package com.birdie.birdie.booking.checkin.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public record CheckInBookingDTO(

        @NotNull
        Long id,

        @JsonAlias(value = "check_in")
        LocalDateTime checkIn

) {
}
