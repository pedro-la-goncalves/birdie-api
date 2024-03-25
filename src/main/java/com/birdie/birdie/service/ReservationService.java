package com.birdie.birdie.service;

import com.birdie.birdie.dto.*;
import com.birdie.birdie.enums.EAdditionalCharges;
import com.birdie.birdie.enums.EDailyRate;
import com.birdie.birdie.enums.EDefaultHours;
import com.birdie.birdie.model.Guest;
import com.birdie.birdie.model.Reservation;
import com.birdie.birdie.repository.GuestRepository;
import com.birdie.birdie.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.naming.NoPermissionException;
import javax.naming.OperationNotSupportedException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.*;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ReservationService {
    @Autowired
    ReservationRepository reservationRepository;

    public ResponseEntity<List<ReservationDTO>> findAll() {
        List<Reservation> reservations = reservationRepository.findAll();

        if (reservations.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.NO_CONTENT)
                    .build();
        }

        List<ReservationDTO> reservationDTOList = reservations.stream().map(ReservationDTO::new).toList();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(reservationDTOList);
    }

    public ResponseEntity<ReservationDTO> findOne(long id) {
        Reservation reservation = reservationRepository.findById(id).orElseThrow();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ReservationDTO(reservation));
    }

    public ResponseEntity<CreatedReservationDTO> create(CreateReservationDTO createReservationDTO) {
        Reservation reservation = new Reservation(createReservationDTO);
        reservation.setEstimatedTotal(getEstimatedTotal(reservation));

        Reservation createdReservation = reservationRepository.save(reservation);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new CreatedReservationDTO(createdReservation));
    }

    public ResponseEntity<ReservationDTO> update(UpdateReservationDTO updateReservationDTO) {
        Reservation reservation = reservationRepository.getReferenceById(updateReservationDTO.id());
        Reservation updatedReservation = reservation.update(updateReservationDTO);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ReservationDTO(updatedReservation));
    }

    public ResponseEntity<CheckedInReservationDTO> checkIn(CheckInReservationDTO checkInReservationDTO) {
        // TODO: Add validation -> field must be of 'yyyy-MM-dd HH:mm' format
        LocalDateTime checkIn = LocalDateTime.parse(checkInReservationDTO.checkIn());

        Reservation reservation = reservationRepository.getReferenceById(checkInReservationDTO.id());

        Reservation updatedReservation = reservation.checkIn(checkIn);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new CheckedInReservationDTO(updatedReservation));
    }

    public ResponseEntity<CheckedOutReservationDTO> checkOut(CheckOutReservationDTO checkOutReservationDTO) {
        LocalDateTime checkOut = LocalDateTime.parse(checkOutReservationDTO.checkOut());

        Reservation reservation = reservationRepository.getReferenceById(checkOutReservationDTO.id());

        // TODO: Add validation -> a "not checked-in reservation" can not check-out
        Reservation updatedReservation = reservation.checkOut(checkOut);

        List<TotalChargedDetailDTO> totalChargedDetails = getTotalChargedDetails(reservation);

        updatedReservation.setTotalCharged(getTotalCharged(reservation));

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new CheckedOutReservationDTO(updatedReservation, totalChargedDetails));
    }

    public ResponseEntity<Void> delete(long id) {
        Optional<Reservation> reservation = reservationRepository.findById(id);

        if (reservation.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }

        reservationRepository.deleteById(id);

        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }

    // TODO: Adapt other methods to consider the soft delete logic
    public ResponseEntity<Void> softDelete(long id) {
        Reservation reservation = reservationRepository.getReferenceById(id);
        reservation.softDelete();

        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }

    public List<TotalChargedDetailDTO> getTotalChargedDetails(Reservation reservation) {
        List<TotalChargedDetailDTO> totalChargedDetails = new ArrayList<>();
        boolean isGuestUsingParkingLot = reservation.isParking();
        boolean isGuestCheckingOutLate = isGuestCheckingOutLate(reservation.getScheduledDeparture(), reservation.getCheckOut());

        if (isGuestUsingParkingLot) {
            int numberOfWorkingDays = getNumberOfWorkingDays(reservation.getScheduledEntry(), reservation.getScheduledDeparture());
            int numberOfWeekendDays = getNumberOfWeekendDays(reservation.getScheduledEntry(), reservation.getScheduledDeparture());

            if (numberOfWorkingDays > 0) {
                double value = EAdditionalCharges.PARKING_WORKDAY.getValue() * numberOfWorkingDays;
                totalChargedDetails.add(new TotalChargedDetailDTO("Estacionamento (dias úteis)", value));
            }

            if (numberOfWeekendDays > 0) {
                double value = EAdditionalCharges.PARKING_WEEKEND.getValue() * numberOfWeekendDays;
                totalChargedDetails.add(new TotalChargedDetailDTO(String.valueOf(EAdditionalCharges.PARKING_WEEKEND), value));
            }
        }

        if (isGuestCheckingOutLate) {
            double value = getLateCheckoutFee(isWeekend(reservation.getCheckOut().toLocalDate()));
            totalChargedDetails.add(new TotalChargedDetailDTO(String.valueOf(EAdditionalCharges.LATE_CHECKOUT), value));
        }

        return totalChargedDetails;
    }

    public double getTotalCharged(Reservation reservation) {
        double totalCharged = reservation.getEstimatedTotal();

        totalCharged += getSumOfAdditionalCharges(reservation);

        return totalCharged;
    }

    public double getEstimatedTotal(Reservation reservation) {
        double estimatedTotal = 0.0;
        boolean isGuestUsingParkingLot = reservation.isParking();

        if (isGuestUsingParkingLot) estimatedTotal += getParkingFee(
                getNumberOfWorkingDays(reservation.getScheduledEntry(), reservation.getScheduledDeparture()),
                getNumberOfWeekendDays(reservation.getScheduledEntry(), reservation.getScheduledDeparture())
        );

        estimatedTotal += getSumOfDailyRates(reservation.getScheduledEntry(), reservation.getScheduledDeparture());

        return estimatedTotal;
    }

    public double getSumOfAdditionalCharges(Reservation reservation) {
        double sumOfAdditionalCharges = 0.0;
        boolean isGuestCheckingOutLate = isGuestCheckingOutLate(reservation.getScheduledDeparture(), reservation.getCheckOut());

        if (isGuestCheckingOutLate) sumOfAdditionalCharges += getLateCheckoutFee(isWeekend(reservation.getCheckOut().toLocalDate()));

        return sumOfAdditionalCharges;
    }

    public double getParkingFee(int numberOfWorkingDays, int numberOfWeekendDays) {
        double parkingFee = 0.0;

        if (numberOfWorkingDays > 0) parkingFee += getRoundedUpWithDecimalsValue(EAdditionalCharges.PARKING_WORKDAY.getValue() * numberOfWorkingDays);
        if (numberOfWeekendDays > 0) parkingFee += getRoundedUpWithDecimalsValue(EAdditionalCharges.PARKING_WEEKEND.getValue() * numberOfWeekendDays);

        return parkingFee;
    }

    public double getLateCheckoutFee(boolean isWeekend) {
        return getRoundedUpWithDecimalsValue(0.5 * (isWeekend ? EDailyRate.WEEKEND.getValue() : EDailyRate.WORKDAY.getValue()));
    }

    public boolean isGuestCheckingOutLate(LocalDate scheduledDeparture, LocalDateTime checkOut) {
        LocalDate checkoutDate = checkOut.toLocalDate();

        if (checkoutDate.isBefore(scheduledDeparture)) return false;
        if (checkoutDate.isAfter(scheduledDeparture)) return true;

        LocalTime deadlineForCheckingOut = LocalTime.parse(EDefaultHours.CHECK_OUT.getValue());
        LocalTime guestCheckoutTime = checkOut.toLocalTime();

        return guestCheckoutTime.isAfter(deadlineForCheckingOut);
    }

    public double getSumOfDailyRates(LocalDate scheduledEntry, LocalDate scheduledDeparture) {
        int numberOfWeekendDays = getNumberOfWeekendDays(scheduledEntry, scheduledDeparture);
        int numberOfWorkingDays = getNumberOfWorkingDays(scheduledEntry, scheduledDeparture);

        double totalOfWeekendDaysRate = EDailyRate.WEEKEND.getValue() * numberOfWeekendDays;
        double totalOfWorkingDaysRate = EDailyRate.WORKDAY.getValue() * numberOfWorkingDays;

        return getRoundedUpWithDecimalsValue(totalOfWeekendDaysRate + totalOfWorkingDaysRate);
    }

    public double getRoundedUpWithDecimalsValue(double totalRate) {
        BigDecimal totalRateAsBigDecimal = new BigDecimal(Double.toString(totalRate));
        return totalRateAsBigDecimal.setScale(2, RoundingMode.HALF_UP).doubleValue();
    }

    public int getNumberOfWorkingDays(LocalDate scheduledEntry, LocalDate scheduledDeparture) {
        int totalNumberOfDays = getDurationInDays(scheduledEntry, scheduledDeparture);
        int numberOfWorkingDays = 0;

        for (int days = 0; days < totalNumberOfDays; days++) {
            if (!isWeekend(scheduledEntry.plusDays(days))) numberOfWorkingDays += 1;
        }

        return numberOfWorkingDays;
    }

    public int getNumberOfWeekendDays(LocalDate scheduledEntry, LocalDate scheduledDeparture) {
        int totalNumberOfDays = getDurationInDays(scheduledEntry, scheduledDeparture);
        int numberOfWeekendDays = 0;

        for (int days = 0; days < totalNumberOfDays; days++) {
            if (isWeekend(scheduledEntry.plusDays(days))) numberOfWeekendDays += 1;
        }

        return numberOfWeekendDays;
    }

    public boolean isWeekend(LocalDate date) {
        DayOfWeek day = DayOfWeek.of(date.get(ChronoField.DAY_OF_WEEK));
        return day == DayOfWeek.SUNDAY || day == DayOfWeek.SATURDAY;
    }

    public int getDurationInDays(LocalDate scheduledEntry, LocalDate scheduledDeparture) {
        return (int) (Duration.between(scheduledEntry.atTime(0, 0), scheduledDeparture.atTime(0, 0)).toDays());
    }
}
