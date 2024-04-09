package com.birdie.api.booking.reservation;

import com.birdie.api.booking.reservation.dto.ReservationCreationDTO;
import com.birdie.api.booking.reservation.dto.ReservationUpdateDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/booking/reservation")
public class ReservationController {

    @Autowired
    ReservationService reservationService;

    @GetMapping
    ResponseEntity<List<Reservation>> findAll() { return this.reservationService.findAll(); }

    @GetMapping(value = "/{id}")
    ResponseEntity<Reservation> findOne(@PathVariable(value = "id") Long id) { return this.reservationService.findOne(id); }

    @PostMapping
    @Transactional
    ResponseEntity<Reservation> create(@RequestBody @Valid ReservationCreationDTO booking) { return this.reservationService.create(booking); }

    @PutMapping
    @Transactional
    ResponseEntity<Reservation> update(@RequestBody @Valid ReservationUpdateDTO booking) { return this.reservationService.update(booking); }

    @DeleteMapping(value = "/{id}")
    ResponseEntity<Void> delete(@PathVariable(value = "id") long id) {
        return this.reservationService.delete(id);
    }

}
