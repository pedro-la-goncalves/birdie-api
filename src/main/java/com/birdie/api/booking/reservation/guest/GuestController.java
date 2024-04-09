package com.birdie.api.booking.reservation.guest;

import com.birdie.api.booking.reservation.guest.dto.GuestCreationDTO;
import com.birdie.api.booking.reservation.guest.dto.GuestDTO;
import com.birdie.api.booking.reservation.guest.dto.GuestUpdateDTO;
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
@RequestMapping("/api/booking/reservation/guest")
public class GuestController {

    @Autowired
    GuestService guestService;

    @GetMapping
    ResponseEntity<Page<GuestDTO>> findAll(Pageable pageable) { return this.guestService.findAll(pageable); }

    @GetMapping(value = "/{id}")
    ResponseEntity<GuestDTO> findOne(@PathVariable(value = "id") Long id) { return this.guestService.findOne(id); }

    @PostMapping
    @Transactional
    ResponseEntity<GuestDTO> create(@RequestBody @Valid GuestCreationDTO guest, UriComponentsBuilder uriComponentsBuilder) { return this.guestService.create(guest, uriComponentsBuilder); }

    @PutMapping
    @Transactional
    ResponseEntity<Guest> update(@RequestBody @Valid GuestUpdateDTO guest) { return this.guestService.update(guest); }

    @DeleteMapping(value = "/{id}")
    ResponseEntity<Void> delete(@PathVariable(value = "id") long id) {
        return this.guestService.delete(id);
    }

}
