package com.birdie.birdie.booking.accommodation.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@JsonIgnoreProperties(ignoreUnknown = true)
public record AccommodationCreationDTO(

        @NotNull
        Long id,

        @NotBlank
        String title,

        @NotBlank
        String description,

        @NotNull
        Double price

) {
}
