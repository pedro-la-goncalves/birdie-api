package com.birdie.birdie.booking.guest;

import com.birdie.birdie.booking.guest.contact.ContactService;
import com.birdie.birdie.booking.guest.contact.dto.ContactCreationDTO;
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

    @Autowired
    ContactService contactService;

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

        guest.getContacts().forEach(contact -> {
            ContactCreationDTO contactCreationDTO = new ContactCreationDTO(contact.type(), contact.value(), createdGuest);
            this.contactService.create(contactCreationDTO);
        });

        Guest guestWithContacts = this.guestRepository.findById(createdGuest.getId()).orElseThrow();

        return ResponseEntity.status(HttpStatus.CREATED).body(guestWithContacts);
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
