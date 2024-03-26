package com.birdie.birdie.controller;

import com.birdie.birdie.dto.CreateGuestDTO;
import com.birdie.birdie.dto.GuestDTO;
import com.birdie.birdie.dto.UpdateGuestDTO;
import com.birdie.birdie.model.Guest;
import com.birdie.birdie.service.GuestService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/guests")
public class GuestController {
    @Autowired
    GuestService guestService;

    @GetMapping
    ResponseEntity<List<GuestDTO>> findAll() { return guestService.findAll(); }

    @GetMapping(value = "/in-hotel")
    ResponseEntity<List<GuestDTO>> findAllInHotel() { return guestService.findAllInHotel(); }

    @GetMapping(value = "/non-checked-in-with-reservation")
    ResponseEntity<List<GuestDTO>> findAllNonCheckedIn() { return guestService.findAllNonCheckedInWithReservation(); }

    @GetMapping(value = "/{id}")
    ResponseEntity<GuestDTO> findOne(@PathVariable(value = "id") long id) {
        return guestService.findOne(id);
    }

    @PostMapping
    @Transactional
    ResponseEntity<GuestDTO> create(@RequestBody @Valid CreateGuestDTO createGuestDTO) {
        return guestService.create(createGuestDTO);
    }

    @PutMapping
    @Transactional
    ResponseEntity<GuestDTO> update(@RequestBody @Valid UpdateGuestDTO updateGuestDTO) {
        return guestService.update(updateGuestDTO);
    }

    @DeleteMapping(value = "/{id}")
    ResponseEntity<Void> delete(@PathVariable(value = "id") long id) {
        return guestService.delete(id);
    }
}
