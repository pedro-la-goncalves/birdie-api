package com.birdie.birdie.service;

import com.birdie.birdie.enums.EDailyRate;
import com.birdie.birdie.enums.EDefaultHours;
import com.birdie.birdie.model.Reservation;
import com.birdie.birdie.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.*;
import java.time.temporal.ChronoField;
import java.util.List;

@Service
public class ReservationService {
    @Autowired
    ReservationRepository reservationRepository;

    public List<Reservation> findAll() {
        return reservationRepository.findAll();
    }

    public Reservation findOne(long id) {
        return reservationRepository.findById(id).orElseThrow();
    }

    public Reservation create(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    public Reservation update(long id, Reservation updatedReservation) {
        Reservation reservation = reservationRepository.findById(id).orElseThrow();
        reservation.setEntry(updatedReservation.getEntry());
        reservation.setExit(updatedReservation.getExit());
        reservation.setCheckIn(updatedReservation.getCheckIn());
        reservation.setCheckOut(updatedReservation.getCheckOut());
        reservation.setParking(updatedReservation.isParking());
        reservation.setEstimatedTotal(updatedReservation.getEstimatedTotal());
        reservation.setTotalCharged(updatedReservation.getTotalCharged());

        return reservationRepository.save(reservation);
    }

    public void delete(long id) {
        reservationRepository.deleteById(id);
    }

    public double getSumOfAdditionalCharges(Reservation reservation) {
        double sumOfAdditionalCharges = 0.0;
        boolean isGuestCheckingOutLate = isGuestCheckingOutLate(reservation.getExit(), reservation.getCheckOut());
        boolean isGuestUsingParkingLot = reservation.isParking();

        if (isGuestCheckingOutLate) sumOfAdditionalCharges += getLateCheckoutFee(isWeekend(reservation.getCheckOut().toLocalDate()));
        if (isGuestUsingParkingLot) sumOfAdditionalCharges += getParkingFee(
                getNumberOfWorkingDays(reservation.getEntry(), reservation.getExit()),
                getNumberOfWeekendDays(reservation.getEntry(), reservation.getExit())
        );

        return sumOfAdditionalCharges;
    }

    public double getParkingFee(int numberOfWorkingDays, int numberOfWeekendDays) {
        double parkingFee = 0.0;

        if (numberOfWorkingDays > 0) parkingFee += getRoundedUpWithDecimalsValue(EDailyRate.WORKDAY.getValue() * numberOfWorkingDays);
        if (numberOfWeekendDays > 0) parkingFee += getRoundedUpWithDecimalsValue(EDailyRate.WEEKEND.getValue() * numberOfWeekendDays);

        return parkingFee;
    }

    public double getLateCheckoutFee(boolean isWeekend) {
        return getRoundedUpWithDecimalsValue(0.5 * (isWeekend ? EDailyRate.WEEKEND.getValue() : EDailyRate.WORKDAY.getValue()));
    }

    public boolean isGuestCheckingOutLate(LocalDate exit, LocalDateTime checkOut) {
        LocalDate checkoutDate = checkOut.toLocalDate();

        if (checkoutDate.isBefore(exit)) return false;
        if (checkoutDate.isAfter(exit)) return true;

        LocalTime deadlineForCheckingOut = LocalTime.parse(EDefaultHours.CHECK_OUT.getValue());
        LocalTime guestCheckoutTime = checkOut.toLocalTime();

        return guestCheckoutTime.isAfter(deadlineForCheckingOut);
    }

    public double getSumOfDailyRates(LocalDate entry, LocalDate exit) {
        int numberOfWeekendDays = getNumberOfWeekendDays(entry, exit);
        int numberOfWorkingDays = getNumberOfWorkingDays(entry, exit);

        double totalOfWeekendDaysRate = EDailyRate.WEEKEND.getValue() * numberOfWeekendDays;
        double totalOfWorkingDaysRate = EDailyRate.WORKDAY.getValue() * numberOfWorkingDays;

        return getRoundedUpWithDecimalsValue(totalOfWeekendDaysRate + totalOfWorkingDaysRate);
    }

    public double getRoundedUpWithDecimalsValue(double totalRate) {
        BigDecimal totalRateAsBigDecimal = new BigDecimal(Double.toString(totalRate));
        return totalRateAsBigDecimal.setScale(2, RoundingMode.HALF_UP).doubleValue();
    }

    public int getNumberOfWorkingDays(LocalDate entry, LocalDate exit) {
        int totalNumberOfDays = getDurationInDays(entry, exit);
        int numberOfWorkingDays = 0;

        for (int days = 0; days < totalNumberOfDays; days++) {
            if (!isWeekend(entry.plusDays(days))) numberOfWorkingDays += 1;
        }

        return numberOfWorkingDays;
    }

    public int getNumberOfWeekendDays(LocalDate entry, LocalDate exit) {
        int totalNumberOfDays = getDurationInDays(entry, exit);
        int numberOfWeekendDays = 0;

        for (int days = 0; days < totalNumberOfDays; days++) {
            if (isWeekend(entry.plusDays(days))) numberOfWeekendDays += 1;
        }

        return numberOfWeekendDays;
    }

    public boolean isWeekend(LocalDate date) {
        DayOfWeek day = DayOfWeek.of(date.get(ChronoField.DAY_OF_WEEK));
        return day == DayOfWeek.SUNDAY || day == DayOfWeek.SATURDAY;
    }

    public int getDurationInDays(LocalDate entry, LocalDate exit) {
        return (int) (Duration.between(entry.atTime(0, 0), exit.atTime(0, 0)).toDays() + 1);
    }
}
