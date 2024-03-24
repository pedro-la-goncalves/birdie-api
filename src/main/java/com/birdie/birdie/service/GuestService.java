package com.birdie.birdie.service;

import com.birdie.birdie.dto.CreateGuestDTO;
import com.birdie.birdie.dto.GuestDTO;
import com.birdie.birdie.dto.UpdateGuestDTO;
import com.birdie.birdie.model.Guest;
import com.birdie.birdie.repository.GuestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GuestService {
    @Autowired
    GuestRepository guestRepository;

    public ResponseEntity<List<GuestDTO>> findAll() {
        List<Guest> guests = guestRepository.findAll();

        if (guests.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.NO_CONTENT)
                    .build();
        }

        List<GuestDTO> guestDTOList = guests.stream().map(GuestDTO::new).toList();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(guestDTOList);
    }

    public ResponseEntity<GuestDTO> findOne(long id) {
        Optional<Guest> guest = guestRepository.findById(id);

        if (guest.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }

        GuestDTO guestDTO = new GuestDTO(guest.get());

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(guestDTO);
    }

    public ResponseEntity<GuestDTO> create(CreateGuestDTO createGuestDTO) {
        Guest createdGuest = guestRepository.save(createGuestDTO.toGuest());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new GuestDTO(createdGuest));
    }

    public ResponseEntity<GuestDTO> update(long id, UpdateGuestDTO updateGuestDTO) {
        Optional<Guest> guest = guestRepository.findById(id);

        if (guest.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }

        Guest savedGuest = guestRepository.save(updateGuestDTO.toGuest());

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new GuestDTO(savedGuest));
    }

    public ResponseEntity<Void> delete(long id) {
        Optional<Guest> guest = guestRepository.findById(id);

        if (guest.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }

        guestRepository.deleteById(id);

        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }
}
