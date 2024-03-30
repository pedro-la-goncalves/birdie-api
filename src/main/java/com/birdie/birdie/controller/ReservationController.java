package com.birdie.birdie.controller;

import com.birdie.birdie.dto.*;
import com.birdie.birdie.service.ReservationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {
    @Autowired
    ReservationService reservationService;

    @GetMapping
    ResponseEntity<List<ReservationDTO>> findAll() { return reservationService.findAll(); }

    @GetMapping(value = "/{id}")
    ResponseEntity<ReservationDTO> findOne(@PathVariable(value = "id") long id) {
        return reservationService.findOne(id);
    }

    @PostMapping
    @Transactional
    ResponseEntity<CreatedReservationDTO> create(@RequestBody @Valid CreateReservationDTO createReservationDTO) {
        return reservationService.create(createReservationDTO);
    }

    @PutMapping
    @Transactional
    ResponseEntity<ReservationDTO> update(@RequestBody UpdateReservationDTO updateReservationDTO) {
        return reservationService.update(updateReservationDTO);
    }

    @PatchMapping(value = "/check-in")
    @Transactional
    ResponseEntity<CheckedInReservationDTO> checkIn(@RequestBody CheckInReservationDTO checkInReservationDTO) {
        return reservationService.checkIn(checkInReservationDTO);
    }

    @PatchMapping(value = "/check-out")
    @Transactional
    ResponseEntity<CheckedOutReservationDTO> checkOut(@RequestBody CheckOutReservationDTO checkOutReservationDTO) {
        return reservationService.checkOut(checkOutReservationDTO);
    }

    @DeleteMapping(value = "/{id}")
    ResponseEntity<Void> delete(@PathVariable(value = "id") long id) {
        return reservationService.delete(id);
    }
}
