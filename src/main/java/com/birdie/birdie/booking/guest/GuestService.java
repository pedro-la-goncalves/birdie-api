package com.birdie.birdie.booking.guest;

import com.birdie.birdie.booking.guest.contact.ContactRepository;
import com.birdie.birdie.booking.guest.contact.ContactService;
import com.birdie.birdie.booking.guest.dto.GuestCreationDTO;
import com.birdie.birdie.booking.guest.dto.GuestDTO;
import com.birdie.birdie.booking.guest.dto.GuestUpdateDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Service
public class GuestService {

    @Autowired
    GuestRepository guestRepository;

    @Autowired
    ContactRepository contactRepository;

    @Autowired
    ContactService contactService;

    public ResponseEntity<List<GuestDTO>> findAll() {
        List<GuestDTO> guests = this.guestRepository.findAll().stream().map(GuestDTO::new).toList();
        return ResponseEntity.ok(guests);
    }

    public ResponseEntity<GuestDTO> findOne(Long id) {
        GuestDTO guest = new GuestDTO(this.guestRepository.findById(id).orElseThrow());
        return ResponseEntity.ok(guest);
    }

    public ResponseEntity<GuestDTO> create(GuestCreationDTO guest, UriComponentsBuilder uriComponentsBuilder) {
        Guest newGuest = new Guest(guest);
        Guest createdGuest = this.guestRepository.save(newGuest);
        GuestDTO guestDTO = new GuestDTO(createdGuest);
        URI uri = uriComponentsBuilder.path("/api/booking/guest/{id}").buildAndExpand(guestDTO.id()).toUri();
        return ResponseEntity.created(uri).body(guestDTO);
    }

    public ResponseEntity<Guest> update(GuestUpdateDTO guest) {
        Guest oldGuest = this.guestRepository.getReferenceById(guest.id());
        Guest updatedGuest = oldGuest.update(guest);
        return ResponseEntity.ok(updatedGuest);
    }

    public ResponseEntity<Void> delete(Long id) {
        this.guestRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
