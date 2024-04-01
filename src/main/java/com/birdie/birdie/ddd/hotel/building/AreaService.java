package com.birdie.birdie.ddd.hotel.building;

import com.birdie.birdie.ddd.hotel.building.dto.AreaCreationDTO;
import com.birdie.birdie.ddd.hotel.building.dto.AreaUpdateDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AreaService {

    @Autowired
    AreaRepository areaRepository;

    public ResponseEntity<List<Area>> findAll() {
        List<Area> area = this.areaRepository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(bookings);
    }

    public ResponseEntity<Area> findOne(Long id) {
        Area area = this.areaRepository.findById(id).orElseThrow();
        return ResponseEntity.status(HttpStatus.OK).body(booking);
    }

    public ResponseEntity<Area> create(AreaCreationDTO area) {
        Area newArea = new Area(area);
        Area createdArea = this.areaRepository.save(newArea);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdArea);
    }

    public ResponseEntity<Area> update(AreaUpdateDTO area) {
        Area oldArea = this.areaRepository.getReferenceById(area.id());
        Area updatedArea = oldArea.update(area);
        return ResponseEntity.status(HttpStatus.OK).body(updatedArea);
    }

    public ResponseEntity<Void> delete(Long id) {
        this.areaRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
