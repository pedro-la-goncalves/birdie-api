package com.birdie.birdie.ddd.hotel.booking.accommodation.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

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
