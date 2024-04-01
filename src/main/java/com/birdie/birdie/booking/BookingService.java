package com.birdie.birdie.booking;

import com.birdie.birdie.booking.dto.BookingCreationDTO;
import com.birdie.birdie.booking.dto.BookingUpdateDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingService {

    @Autowired
    BookingRepository bookingRepository;

    public ResponseEntity<List<Booking>> findAll() {
        List<Booking> bookings = this.bookingRepository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(bookings);
    }

    public ResponseEntity<Booking> findOne(Long id) {
        Booking booking = this.bookingRepository.findById(id).orElseThrow();
        return ResponseEntity.status(HttpStatus.OK).body(booking);
    }

    public ResponseEntity<Booking> create(BookingCreationDTO booking) {
        Booking newBooking = new Booking(booking);
        Booking createdBooking = this.bookingRepository.save(newBooking);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdBooking);
    }

    public ResponseEntity<Booking> update(BookingUpdateDTO booking) {
        Booking oldBooking = this.bookingRepository.getReferenceById(booking.id());
        Booking updatedBooking = oldBooking.update(booking);
        return ResponseEntity.status(HttpStatus.OK).body(updatedBooking);
    }

    public ResponseEntity<Void> delete(Long id) {
        this.bookingRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
