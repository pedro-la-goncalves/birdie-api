package com.birdie.birdie.ddd.hotel.booking.accommodation;

import com.birdie.birdie.ddd.hotel.booking.accommodation.dto.AccommodationCreationDTO;
import com.birdie.birdie.ddd.hotel.booking.accommodation.dto.AccommodationUpdateDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccommodationService {

    @Autowired
    AccommodationRepository accommodationRepository;

    public ResponseEntity<List<Accommodation>> findAll() {
        List<Accommodation> accommodations = this.accommodationRepository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(accommodations);
    }

    public ResponseEntity<Accommodation> findOne(Long id) {
        Accommodation accommodation = this.accommodationRepository.findById(id).orElseThrow();
        return ResponseEntity.status(HttpStatus.OK).body(accommodation);
    }

    public ResponseEntity<Accommodation> create(AccommodationCreationDTO area) {
        Accommodation newAccommodation = new Accommodation(area);
        Accommodation createdAccommodation = this.accommodationRepository.save(newAccommodation);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAccommodation);
    }

    public ResponseEntity<Accommodation> update(AccommodationUpdateDTO area) {
        Accommodation oldAccommodation = this.accommodationRepository.getReferenceById(area.id());
        Accommodation updatedAccommodation = oldAccommodation.update(area);
        return ResponseEntity.status(HttpStatus.OK).body(updatedAccommodation);
    }

    public ResponseEntity<Void> delete(Long id) {
        this.accommodationRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
