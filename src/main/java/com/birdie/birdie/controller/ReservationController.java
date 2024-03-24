package com.birdie.birdie.controller;

import com.birdie.birdie.dto.CreateReservationDTO;
import com.birdie.birdie.dto.ReservationDTO;
import com.birdie.birdie.dto.UpdateReservationDTO;
import com.birdie.birdie.model.Reservation;
import com.birdie.birdie.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ReservationController {
    @Autowired
    ReservationService reservationService;

    @GetMapping(value = "/reservations")
    ResponseEntity<List<ReservationDTO>> findAll() { return reservationService.findAll(); }

    @GetMapping(value = "/reservations/{id}")
    ResponseEntity<ReservationDTO> findOne(@PathVariable(value = "id") long id) {
        return reservationService.findOne(id);
    }

    @PostMapping(value = "/reservations")
    ResponseEntity<ReservationDTO> create(@RequestBody CreateReservationDTO createReservationDTO) {
        return reservationService.create(createReservationDTO);
    }

    @PutMapping(value = "/reservations/{id}")
    ResponseEntity<ReservationDTO> update(@PathVariable(value = "id") long id, @RequestBody UpdateReservationDTO updateReservationDTO) {
        return reservationService.update(id, updateReservationDTO);
    }

    @DeleteMapping(value = "/reservations/{id}")
    ResponseEntity<Void> delete(@PathVariable(value = "id") long id) {
        return reservationService.delete(id);
    }
}
