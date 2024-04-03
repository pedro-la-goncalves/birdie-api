package com.birdie.birdie.booking.guest;

import com.birdie.birdie.booking.guest.contact.Contact;
import com.birdie.birdie.booking.guest.contact.ContactRepository;
import com.birdie.birdie.booking.guest.contact.ContactService;
import com.birdie.birdie.booking.guest.dto.GuestCreationDTO;
import com.birdie.birdie.booking.guest.dto.GuestDTO;
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
    ContactRepository contactRepository;

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

    public ResponseEntity<GuestDTO> create(GuestCreationDTO guest) {
        Guest newGuest = new Guest(guest);
        Guest createdGuest = this.guestRepository.save(newGuest);

        // create contacts
        List<Contact> contacts = guest.getContacts().stream().map(contact -> {
            Contact newContact = new Contact(contact.type(), contact.value(), createdGuest);
            return this.contactRepository.save(newContact);
        }).toList();

        createdGuest.setContacts(contacts);

        GuestDTO guestDTO = new GuestDTO(createdGuest);

        return ResponseEntity.status(HttpStatus.CREATED).body(guestDTO);
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
