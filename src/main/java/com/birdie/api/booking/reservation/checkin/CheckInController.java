package com.birdie.api.booking.reservation.checkin;

import com.birdie.api.booking.reservation.checkin.dto.CheckInReservationDTO;
import com.birdie.api.booking.reservation.checkin.dto.CheckedInReservationDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/booking/reservation/check-in")
public class CheckInController {

    @Autowired
    CheckInService checkInService;

    @PatchMapping
    @Transactional
    ResponseEntity<CheckedInReservationDTO> checkIn(@RequestBody @Valid CheckInReservationDTO booking) {
        return this.checkInService.checkIn(booking);
    }

}
