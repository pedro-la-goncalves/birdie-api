package com.birdie.api.booking.reservation.guest.contact;

import com.birdie.api.booking.reservation.guest.contact.dto.ContactCreationDTO;
import com.birdie.api.booking.reservation.guest.contact.dto.ContactUpdateDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/booking/guest/contact")
public class ContactController {

    @Autowired
    ContactService contactService;

    @GetMapping
    ResponseEntity<List<Contact>> findAll() { return this.contactService.findAll(); }

    @GetMapping(value = "/{id}")
    ResponseEntity<Contact> findOne(@PathVariable(value = "id") Long id) { return this.contactService.findOne(id); }

    @PostMapping
    @Transactional
    ResponseEntity<Contact> create(@RequestBody @Valid ContactCreationDTO contact) { return this.contactService.create(contact); }

    @PutMapping
    @Transactional
    ResponseEntity<Contact> update(@RequestBody @Valid ContactUpdateDTO contact) { return this.contactService.update(contact); }

    @DeleteMapping(value = "/{id}")
    ResponseEntity<Void> delete(@PathVariable(value = "id") long id) {
        return this.contactService.delete(id);
    }

}
