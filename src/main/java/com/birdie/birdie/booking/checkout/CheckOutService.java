package com.birdie.birdie.booking.checkout;

import com.birdie.birdie.booking.Booking;
import com.birdie.birdie.booking.BookingRepository;
import com.birdie.birdie.booking.checkout.dto.CheckOutBookingDTO;
import com.birdie.birdie.booking.checkout.dto.CheckedOutBookingDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CheckOutService {

    @Autowired
    BookingRepository bookingRepository;

    public ResponseEntity<CheckedOutBookingDTO> checkOut(CheckOutBookingDTO booking) {
        Booking oldBooking = this.bookingRepository.getReferenceById(booking.id());
        Booking updatedBooking = oldBooking.setCheckOut(booking.checkOut());
        CheckedOutBookingDTO checkedOutBooking = new CheckedOutBookingDTO(updatedBooking);
        return ResponseEntity.status(HttpStatus.OK).body(checkedOutBooking);
    }

}
