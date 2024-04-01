package com.birdie.birdie.booking.checkin.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record CheckInBookingDTO(

        @NotNull
        Long id,

        @JsonAlias(value = "check_in")
        LocalDateTime checkIn

) {
}
