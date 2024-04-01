package com.birdie.birdie.booking.accommodation.dto;

import jakarta.validation.constraints.NotNull;

public record AccommodationDTO(

        @NotNull
        Long id,

        String title,

        String description,

        Double price

) {
}