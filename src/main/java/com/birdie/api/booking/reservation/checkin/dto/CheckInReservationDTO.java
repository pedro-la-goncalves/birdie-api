package com.birdie.api.booking.reservation.checkin.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public record CheckInReservationDTO(

        @NotNull
        Long id,

        @JsonAlias(value = "check_in")
        LocalDateTime checkIn

) {
}
