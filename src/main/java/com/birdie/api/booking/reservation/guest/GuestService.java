package com.birdie.api.booking.reservation.guest;

import com.birdie.api.booking.reservation.guest.dto.GuestCreationDTO;
import com.birdie.api.booking.reservation.guest.dto.GuestDTO;
import com.birdie.api.booking.reservation.guest.dto.GuestUpdateDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
public class GuestService {

    @Autowired
    GuestRepository guestRepository;

    public ResponseEntity<Page<GuestDTO>> findAll(@PageableDefault(size = 10, sort = "id") Pageable pageable) {
        Page<GuestDTO> guests = this.guestRepository.findAll(pageable).map(GuestDTO::new);
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
        Guest guest = this.guestRepository.findById(id).orElseThrow();
        this.guestRepository.deleteById(guest.getId());
        return ResponseEntity.noContent().build();
    }

}
