package com.birdie.birdie.booking.checkin;

import com.birdie.birdie.booking.Booking;
import com.birdie.birdie.booking.BookingRepository;
import com.birdie.birdie.booking.checkin.dto.CheckInBookingDTO;
import com.birdie.birdie.booking.checkin.dto.CheckedInBookingDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CheckInService {

    @Autowired
    BookingRepository bookingRepository;

    public ResponseEntity<CheckedInBookingDTO> checkIn(CheckInBookingDTO booking) {
        Booking oldBooking = this.bookingRepository.getReferenceById(booking.id());
        Booking updatedBooking = oldBooking.setCheckIn(booking.checkIn());
        CheckedInBookingDTO checkedInBooking = new CheckedInBookingDTO(updatedBooking);
        return ResponseEntity.status(HttpStatus.OK).body(checkedInBooking);
    }

}
