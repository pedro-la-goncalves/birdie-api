package com.birdie.birdie.guest;

import com.birdie.birdie.guest.Guest;
import com.birdie.birdie.guest.GuestRepository;
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

    public Guest update(long id, Guest updatedGuest) {
        Guest guest = guestRepository.findById(id).orElseThrow();
        guest.setName(updatedGuest.getName());
        guest.setDocument(updatedGuest.getDocument());

        return guestRepository.save(guest);
    }

    public void delete(long id) {
        guestRepository.deleteById(id);
    }
}
