package com.birdie.api.booking.reservation.guest.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

@JsonIgnoreProperties(ignoreUnknown = true)
public record GuestUpdateDTO(

        @NotNull
        Long id,

        String name,

        String surname,

        String socialName,

        LocalDate birthdate

) {
}
