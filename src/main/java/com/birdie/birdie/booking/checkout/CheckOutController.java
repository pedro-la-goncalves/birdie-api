package com.birdie.birdie.booking.checkout;

import com.birdie.birdie.booking.checkout.dto.CheckOutBookingDTO;
import com.birdie.birdie.booking.checkout.dto.CheckedOutBookingDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/hotel/bookings/check-in")
public class CheckOutController {

    @Autowired
    CheckOutService checkOutService;

    @PatchMapping
    @Transactional
    ResponseEntity<CheckedOutBookingDTO> checkOut(@RequestBody @Valid CheckOutBookingDTO booking) {
        return this.checkOutService.checkOut(booking);
    }

}
