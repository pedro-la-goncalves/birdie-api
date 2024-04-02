package com.birdie.birdie.booking.guest;

import com.birdie.birdie.booking.guest.dto.GuestCreationDTO;
import com.birdie.birdie.booking.guest.dto.GuestUpdateDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/booking/guest")
public class GuestController {

    @Autowired
    GuestService guestService;

    @GetMapping
    ResponseEntity<List<Guest>> findAll() { return this.guestService.findAll(); }

    @GetMapping(value = "/{id}")
    ResponseEntity<Guest> findOne(@PathVariable(value = "id") Long id) { return this.guestService.findOne(id); }

    @PostMapping
    @Transactional
    ResponseEntity<Guest> create(@RequestBody @Valid GuestCreationDTO guest) { return this.guestService.create(guest); }

    @PutMapping
    @Transactional
    ResponseEntity<Guest> update(@RequestBody @Valid GuestUpdateDTO guest) { return this.guestService.update(guest); }

    @DeleteMapping(value = "/{id}")
    ResponseEntity<Void> delete(@PathVariable(value = "id") long id) {
        return this.guestService.delete(id);
    }

}
