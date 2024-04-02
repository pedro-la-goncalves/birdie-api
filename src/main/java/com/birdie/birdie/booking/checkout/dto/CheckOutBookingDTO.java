package com.birdie.birdie.booking.checkout.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record CheckOutBookingDTO(

        @NotNull
        Long id,

        @JsonAlias(value = "check_out")
        LocalDateTime checkOut

) {
}
