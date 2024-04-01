package com.birdie.birdie.ddd.hotel.building;

import com.birdie.birdie.ddd.hotel.booking.dto.BookingCreationDTO;
import com.birdie.birdie.ddd.hotel.booking.dto.BookingUpdateDTO;
import com.birdie.birdie.ddd.hotel.booking.Booking;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/hotel/rooms/bookings")
public class AreaController {

    @Autowired
    AreaService areaService;

    @GetMapping
    ResponseEntity<List<Booking>> findAll() { return this.areaService.findAll(); }

    @GetMapping(value = "/{id}")
    ResponseEntity<Booking> findOne(@PathVariable(value = "id") Long id) { return this.areaService.findOne(id); }

    @PostMapping
    @Transactional
    ResponseEntity<Booking> create(@RequestBody @Valid BookingCreationDTO booking) { return this.areaService.create(booking); }

    @PutMapping
    @Transactional
    ResponseEntity<Booking> update(@RequestBody @Valid BookingUpdateDTO booking) { return this.areaService.update(booking); }

    @DeleteMapping(value = "/{id}")
    ResponseEntity<Void> delete(@PathVariable(value = "id") long id) {
        return this.areaService.delete(id);
    }

}
