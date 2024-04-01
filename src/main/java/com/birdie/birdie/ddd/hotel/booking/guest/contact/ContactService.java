package com.birdie.birdie.ddd.hotel.booking.guest.contact;

import com.birdie.birdie.ddd.hotel.booking.guest.contact.dto.ContactCreationDTO;
import com.birdie.birdie.ddd.hotel.booking.guest.contact.dto.ContactUpdateDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactService {

    @Autowired
    ContactRepository contactRepository;

    public ResponseEntity<List<Contact>> findAll() {
        List<Contact> contacts = this.contactRepository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(contacts);
    }

    public ResponseEntity<Contact> findOne(Long id) {
        Contact contact = this.contactRepository.findById(id).orElseThrow();
        return ResponseEntity.status(HttpStatus.OK).body(contact);
    }

    public ResponseEntity<Contact> create(ContactCreationDTO contact) {
        Contact newContact = new Contact(contact);
        Contact createdContact = this.contactRepository.save(newContact);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdContact);
    }

    public ResponseEntity<Contact> update(ContactUpdateDTO contact) {
        Contact oldContact = this.contactRepository.getReferenceById(contact.id());
        Contact updatedContact = oldContact.update(contact);
        return ResponseEntity.status(HttpStatus.OK).body(updatedContact);
    }

    public ResponseEntity<Void> delete(Long id) {
        this.contactRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
