package com.birdie.birdie.service;

import com.birdie.birdie.model.Guest;
import com.birdie.birdie.repository.GuestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GuestService {
    @Autowired
    GuestRepository guestRepository;

    public List<Guest> findAll() {
        return guestRepository.findAll();
    }

    public Guest findOne(long id) {
        return guestRepository.findById(id).orElseThrow();
    }

    public Guest create(Guest guest) {
        return guestRepository.save(guest);
    }

    public Guest update(Guest guest) {
        return guestRepository.save(guest);
    }

    public void delete(long id) {
        guestRepository.deleteById(id);
    }
}
