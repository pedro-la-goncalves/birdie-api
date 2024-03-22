package com.birdie.birdie.controller;

import com.birdie.birdie.model.Guest;
import com.birdie.birdie.service.GuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class GuestController {
    @Autowired
    GuestService guestService;

    @RequestMapping(value="/guests", method= RequestMethod.GET)
    List<Guest> findAll() { return guestService.findAll(); }

    @RequestMapping(value="/guests/{id}", method= RequestMethod.GET)
    Guest findOne(@PathVariable(value = "id") long id) {
        return guestService.findOne(id);
    }

    @RequestMapping(value="/guests", method= RequestMethod.POST)
    Guest create(Guest guest) {
        return guestService.create(guest);
    }

    @RequestMapping(value="/guests/{id}", method= RequestMethod.PUT)
    Guest update(@PathVariable(value = "id") long id, Guest guest) {
        return guestService.update(id, guest);
    }

    @RequestMapping(value="/guests/{id}", method= RequestMethod.DELETE)
    void delete(@PathVariable(value = "id") long id) {
        guestService.delete(id);
    }
}
