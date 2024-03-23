package com.birdie.birdie.controller;

import com.birdie.birdie.dto.CreateReservationDTO;
import com.birdie.birdie.dto.ReservationDTO;
import com.birdie.birdie.dto.UpdateReservationDTO;
import com.birdie.birdie.model.Reservation;
import com.birdie.birdie.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ReservationController {
    @Autowired
    ReservationService reservationService;

    @RequestMapping(value = "/reservations", method = RequestMethod.GET)
    List<Reservation> findAll() { return reservationService.findAll(); }

    @RequestMapping(value = "/reservations/{id}", method = RequestMethod.GET)
    Reservation findOne(@PathVariable(value = "id") long id) {
        return reservationService.findOne(id);
    }

    @RequestMapping(value = "/reservations", method = RequestMethod.POST)
    Reservation create(CreateReservationDTO createReservationDTO) {
        return reservationService.create(createReservationDTO);
    }

    @RequestMapping(value = "/reservations/{id}", method = RequestMethod.PUT)
    ReservationDTO update(@PathVariable(value = "id") long id, UpdateReservationDTO updateReservationDTO) {
        return reservationService.update(id, updateReservationDTO);
    }

    @RequestMapping(value = "/reservations/{id}", method = RequestMethod.DELETE)
    void delete(@PathVariable(value = "id") long id) {
        reservationService.delete(id);
    }
}
