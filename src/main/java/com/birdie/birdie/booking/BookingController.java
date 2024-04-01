package com.birdie.birdie.booking;

import com.birdie.birdie.booking.dto.BookingCreationDTO;
import com.birdie.birdie.booking.dto.BookingUpdateDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/hotel/bookings")
public class BookingController {

    @Autowired
    BookingService bookingService;

    @GetMapping
    ResponseEntity<List<Booking>> findAll() { return this.bookingService.findAll(); }

    @GetMapping(value = "/{id}")
    ResponseEntity<Booking> findOne(@PathVariable(value = "id") Long id) { return this.bookingService.findOne(id); }

    @PostMapping
    @Transactional
    ResponseEntity<Booking> create(@RequestBody @Valid BookingCreationDTO booking) { return this.bookingService.create(booking); }

    @PutMapping
    @Transactional
    ResponseEntity<Booking> update(@RequestBody @Valid BookingUpdateDTO booking) { return this.bookingService.update(booking); }

    @DeleteMapping(value = "/{id}")
    ResponseEntity<Void> delete(@PathVariable(value = "id") long id) {
        return this.bookingService.delete(id);
    }

}
