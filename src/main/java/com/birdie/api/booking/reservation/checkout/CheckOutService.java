package com.birdie.api.booking.reservation.checkout;

import com.birdie.api.booking.reservation.Reservation;
import com.birdie.api.booking.reservation.ReservationRepository;
import com.birdie.api.booking.reservation.checkout.dto.CheckOutReservationDTO;
import com.birdie.api.booking.reservation.checkout.dto.CheckedOutReservationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CheckOutService {

    @Autowired
    ReservationRepository reservationRepository;

    public ResponseEntity<CheckedOutReservationDTO> checkOut(CheckOutReservationDTO booking) {
        Reservation oldReservation = this.reservationRepository.getReferenceById(booking.id());
        Reservation updatedReservation = oldReservation.setCheckOut(booking.checkOut());
        CheckedOutReservationDTO checkedOutBooking = new CheckedOutReservationDTO(updatedReservation);
        return ResponseEntity.status(HttpStatus.OK).body(checkedOutBooking);
    }

}
