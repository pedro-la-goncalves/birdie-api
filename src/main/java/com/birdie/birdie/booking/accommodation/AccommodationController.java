package com.birdie.birdie.booking.accommodation;

import com.birdie.birdie.booking.accommodation.dto.AccommodationUpdateDTO;
import com.birdie.birdie.booking.accommodation.dto.AccommodationCreationDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/booking/accommodation")
public class AccommodationController {

    @Autowired
    AccommodationService accommodationService;

    @GetMapping
    ResponseEntity<List<Accommodation>> findAll() { return this.accommodationService.findAll(); }

    @GetMapping(value = "/{id}")
    ResponseEntity<Accommodation> findOne(@PathVariable(value = "id") Long id) { return this.accommodationService.findOne(id); }

    @PostMapping
    @Transactional
    ResponseEntity<Accommodation> create(@RequestBody @Valid AccommodationCreationDTO accommodation) { return this.accommodationService.create(accommodation); }

    @PutMapping
    @Transactional
    ResponseEntity<Accommodation> update(@RequestBody @Valid AccommodationUpdateDTO accommodation) { return this.accommodationService.update(accommodation); }

    @DeleteMapping(value = "/{id}")
    ResponseEntity<Void> delete(@PathVariable(value = "id") long id) {
        return this.accommodationService.delete(id);
    }

}
