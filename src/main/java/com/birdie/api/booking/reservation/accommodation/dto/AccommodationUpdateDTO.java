package com.birdie.api.booking.reservation.accommodation.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotNull;

@JsonIgnoreProperties(ignoreUnknown = true)
public record AccommodationUpdateDTO(

        @NotNull
        Long id,

        String title,

        String description,

        Double price

) {
}
