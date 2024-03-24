package com.birdie.birdie.controller;

import com.birdie.birdie.dto.CreateGuestDTO;
import com.birdie.birdie.dto.GuestDTO;
import com.birdie.birdie.dto.UpdateGuestDTO;
import com.birdie.birdie.model.Guest;
import com.birdie.birdie.service.GuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class GuestController {
    @Autowired
    GuestService guestService;

    @RequestMapping(value="/guests", method= RequestMethod.GET)
    ResponseEntity<List<GuestDTO>> findAll() { return guestService.findAll(); }

    @RequestMapping(value="/guests/{id}", method= RequestMethod.GET)
    ResponseEntity<GuestDTO> findOne(@PathVariable(value = "id") long id) {
        return guestService.findOne(id);
    }

    @RequestMapping(value="/guests", method= RequestMethod.POST)
    ResponseEntity<GuestDTO> create(@RequestBody CreateGuestDTO createGuestDTO) {
        return guestService.create(createGuestDTO);
    }

    @RequestMapping(value="/guests/{id}", method= RequestMethod.PUT)
    ResponseEntity<GuestDTO> update(@PathVariable(value = "id") long id, @RequestBody UpdateGuestDTO updateGuestDTO) {
        return guestService.update(id, updateGuestDTO);
    }

    @RequestMapping(value="/guests/{id}", method= RequestMethod.DELETE)
    ResponseEntity<Void> delete(@PathVariable(value = "id") long id) {
        return guestService.delete(id);
    }
}
