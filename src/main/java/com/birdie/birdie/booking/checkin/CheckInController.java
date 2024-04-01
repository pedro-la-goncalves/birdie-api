package com.birdie.birdie.booking.checkin;

import com.birdie.birdie.booking.checkin.dto.CheckInBookingDTO;
import com.birdie.birdie.booking.checkin.dto.CheckedInBookingDTO;
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
public class CheckInController {

    @Autowired
    CheckInService checkInService;

    @PatchMapping
    @Transactional
    ResponseEntity<CheckedInBookingDTO> checkIn(@RequestBody @Valid CheckInBookingDTO booking) {
        return this.checkInService.checkIn(booking);
    }

}
