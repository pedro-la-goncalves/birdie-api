package com.birdie.api.booking.reservation;

import com.birdie.api.booking.reservation.dto.ReservationCreationDTO;
import com.birdie.api.booking.reservation.dto.ReservationDTO;
import com.birdie.api.booking.reservation.dto.ReservationUpdateDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@Controller
@RequestMapping("/api/booking/reservation")
public class ReservationController {

    @Autowired
    ReservationService reservationService;

    @GetMapping
    ResponseEntity<Page<ReservationDTO>> findAll(Pageable pageable) { return this.reservationService.findAll(pageable); }

    @GetMapping(value = "/arriving-today")
    ResponseEntity<Page<ReservationDTO>> findAllArrivingToday(Pageable pageable) { return this.reservationService.findAllArrivingToday(pageable); }

    @GetMapping(value = "/leaving-today")
    ResponseEntity<Page<ReservationDTO>> findAllLeavingToday(Pageable pageable) { return this.reservationService.findAllLeavingToday(pageable); }

    @GetMapping(value = "/{id}")
    ResponseEntity<ReservationDTO> findOne(@PathVariable(value = "id") Long id) { return this.reservationService.findOne(id); }

    @PostMapping
    @Transactional
    ResponseEntity<ReservationDTO> create(@RequestBody @Valid ReservationCreationDTO reservation, UriComponentsBuilder uriComponentsBuilder) { return this.reservationService.create(reservation, uriComponentsBuilder); }

    @PutMapping
    @Transactional
    ResponseEntity<Reservation> update(@RequestBody @Valid ReservationUpdateDTO reservation) { return this.reservationService.update(reservation); }

    @DeleteMapping(value = "/{id}")
    ResponseEntity<Void> delete(@PathVariable(value = "id") long id) {
        return this.reservationService.delete(id);
    }

}
