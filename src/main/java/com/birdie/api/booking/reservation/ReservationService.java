package com.birdie.api.booking.reservation;

import com.birdie.api.booking.reservation.dto.ReservationCreationDTO;
import com.birdie.api.booking.reservation.dto.ReservationUpdateDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationService {

    @Autowired
    ReservationRepository reservationRepository;

    public ResponseEntity<List<Reservation>> findAll() {
        List<Reservation> reservations = this.reservationRepository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(reservations);
    }

    public ResponseEntity<Reservation> findOne(Long id) {
        Reservation reservation = this.reservationRepository.findById(id).orElseThrow();
        return ResponseEntity.status(HttpStatus.OK).body(reservation);
    }

    public ResponseEntity<Reservation> create(ReservationCreationDTO booking) {
        Reservation newReservation = new Reservation(booking);
        Reservation createdReservation = this.reservationRepository.save(newReservation);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdReservation);
    }

    public ResponseEntity<Reservation> update(ReservationUpdateDTO booking) {
        Reservation oldReservation = this.reservationRepository.getReferenceById(booking.id());
        Reservation updatedReservation = oldReservation.update(booking);
        return ResponseEntity.status(HttpStatus.OK).body(updatedReservation);
    }

    public ResponseEntity<Void> delete(Long id) {
        this.reservationRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
