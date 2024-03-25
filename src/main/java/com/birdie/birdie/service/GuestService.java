package com.birdie.birdie.service;

import com.birdie.birdie.dto.CreateGuestDTO;
import com.birdie.birdie.dto.GuestDTO;
import com.birdie.birdie.dto.UpdateGuestDTO;
import com.birdie.birdie.model.Guest;
import com.birdie.birdie.repository.GuestRepository;
import jakarta.persistence.EntityNotFoundException;
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
        Guest guest = guestRepository.findById(id).orElseThrow();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new GuestDTO(guest));
    }

    public ResponseEntity<GuestDTO> create(CreateGuestDTO createGuestDTO) {
        Guest createdGuest = guestRepository.save(new Guest(createGuestDTO));

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new GuestDTO(createdGuest));
    }

    public ResponseEntity<GuestDTO> update(UpdateGuestDTO updateGuestDTO) {
        Guest guest = guestRepository.getReferenceById(updateGuestDTO.id());
        Guest updatedGuest = guest.update(updateGuestDTO);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new GuestDTO(updatedGuest));
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

    // TODO: Adaptar outros métodos para considerar exclusão lógica
    public ResponseEntity<Void> softDelete(long id) {
        Guest guest = guestRepository.getReferenceById(id);
        guest.softDelete();

        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }
}
