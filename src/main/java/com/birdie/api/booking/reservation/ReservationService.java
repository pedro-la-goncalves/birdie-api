package com.birdie.api.booking.reservation;

import com.birdie.api.booking.reservation.dto.ReservationCreationDTO;
import com.birdie.api.booking.reservation.dto.ReservationDTO;
import com.birdie.api.booking.reservation.dto.ReservationUpdateDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;

@Service
public class ReservationService {

    @Autowired
    ReservationRepository reservationRepository;

    public ResponseEntity<Page<ReservationDTO>> findAll(@PageableDefault(size = 10, sort = "id") Pageable pageable) {
        Page<ReservationDTO> reservations = this.reservationRepository.findAll(pageable).map(ReservationDTO::new);
        return ResponseEntity.ok(reservations);
    }

    public ResponseEntity<Page<ReservationDTO>> findAllArrivingToday(@PageableDefault(size = 10, sort = "id") Pageable pageable) {
        LocalDate today = LocalDate.now();
        Page<ReservationDTO> reservations = this.reservationRepository.findAllByArrivalEqualsOrderByGuest_name(pageable, today).map(ReservationDTO::new);
        return ResponseEntity.ok(reservations);
    }

    public ResponseEntity<Page<ReservationDTO>> findAllLeavingToday(@PageableDefault(size = 10, sort = "id") Pageable pageable) {
        LocalDate today = LocalDate.now();
        Page<ReservationDTO> reservations = this.reservationRepository.findAllByDepartureEqualsOrderByGuest_name(pageable, today).map(ReservationDTO::new);
        return ResponseEntity.ok(reservations);
    }

    public ResponseEntity<ReservationDTO> findOne(Long id) {
        ReservationDTO reservation = new ReservationDTO(this.reservationRepository.findById(id).orElseThrow());
        return ResponseEntity.ok(reservation);
    }

    public ResponseEntity<ReservationDTO> create(ReservationCreationDTO reservation, UriComponentsBuilder uriComponentsBuilder) {
        Reservation newReservation = new Reservation(reservation);
        Reservation createdReservation = this.reservationRepository.save(newReservation);
        ReservationDTO reservationDTO = new ReservationDTO(createdReservation);
        URI uri = uriComponentsBuilder.path("/api/booking/reservation/{id}").buildAndExpand(reservationDTO.id()).toUri();
        return ResponseEntity.created(uri).body(reservationDTO);
    }

    public ResponseEntity<Reservation> update(ReservationUpdateDTO reservation) {
        Reservation oldReservation = this.reservationRepository.getReferenceById(reservation.id());
        Reservation updatedReservation = oldReservation.update(reservation);
        return ResponseEntity.ok(updatedReservation);
    }

    public ResponseEntity<Void> delete(Long id) {
        Reservation reservation = this.reservationRepository.findById(id).orElseThrow();
        this.reservationRepository.deleteById(reservation.getId());
        return ResponseEntity.noContent().build();
    }

}
