package com.birdie.api.booking.reservation.checkin.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public record CheckInReservationDTO(

        @NotNull
        Long id,

        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm")
        @PastOrPresent
        LocalDateTime checkIn

) {
}
