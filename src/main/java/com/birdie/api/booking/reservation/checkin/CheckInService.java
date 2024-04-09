package com.birdie.api.booking.reservation.checkin;

import com.birdie.api.booking.reservation.Reservation;
import com.birdie.api.booking.reservation.ReservationRepository;
import com.birdie.api.booking.reservation.checkin.dto.CheckInReservationDTO;
import com.birdie.api.booking.reservation.checkin.dto.CheckedInReservationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CheckInService {

    @Autowired
    ReservationRepository reservationRepository;

    public ResponseEntity<CheckedInReservationDTO> checkIn(CheckInReservationDTO booking) {
        Reservation oldReservation = this.reservationRepository.getReferenceById(booking.id());
        Reservation updatedReservation = oldReservation.setCheckIn(booking.checkIn());
        CheckedInReservationDTO checkedInBooking = new CheckedInReservationDTO(updatedReservation);
        return ResponseEntity.status(HttpStatus.OK).body(checkedInBooking);
    }

}
