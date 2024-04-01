package com.birdie.birdie.booking.guest;

import com.birdie.birdie.booking.guest.dto.GuestCreationDTO;
import com.birdie.birdie.booking.guest.dto.GuestUpdateDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GuestService {

    @Autowired
    GuestRepository guestRepository;

    public ResponseEntity<List<Guest>> findAll() {
        List<Guest> guests = this.guestRepository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(guests);
    }

    public ResponseEntity<Guest> findOne(Long id) {
        Guest guest = this.guestRepository.findById(id).orElseThrow();
        return ResponseEntity.status(HttpStatus.OK).body(guest);
    }

    public ResponseEntity<Guest> create(GuestCreationDTO guest) {
        Guest newGuest = new Guest(guest);
        Guest createdGuest = this.guestRepository.save(newGuest);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdGuest);
    }

    public ResponseEntity<Guest> update(GuestUpdateDTO guest) {
        Guest oldGuest = this.guestRepository.getReferenceById(guest.id());
        Guest updatedGuest = oldGuest.update(guest);
        return ResponseEntity.status(HttpStatus.OK).body(updatedGuest);
    }

    public ResponseEntity<Void> delete(Long id) {
        this.guestRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
