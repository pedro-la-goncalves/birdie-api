package com.birdie.api.booking.reservation.checkout;

import com.birdie.api.booking.reservation.checkout.dto.CheckOutReservationDTO;
import com.birdie.api.booking.reservation.checkout.dto.CheckedOutReservationDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/booking/reservation/check-out")
public class CheckOutController {

    @Autowired
    CheckOutService checkOutService;

    @PatchMapping
    @Transactional
    ResponseEntity<CheckedOutReservationDTO> checkOut(@RequestBody @Valid CheckOutReservationDTO booking) {
        return this.checkOutService.checkOut(booking);
    }

}
