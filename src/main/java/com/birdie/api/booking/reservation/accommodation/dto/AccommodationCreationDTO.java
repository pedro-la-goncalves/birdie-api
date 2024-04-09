package com.birdie.api.booking.reservation.accommodation.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@JsonIgnoreProperties(ignoreUnknown = true)
public record AccommodationCreationDTO(

        @NotBlank
        String title,

        @NotBlank
        String description,

        @NotNull
        Double price

) {
}
