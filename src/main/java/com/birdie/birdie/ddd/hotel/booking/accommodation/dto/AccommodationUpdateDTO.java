package com.birdie.birdie.ddd.hotel.booking.accommodation.dto;

import jakarta.validation.constraints.NotNull;

public record AccommodationUpdateDTO(

        @NotNull
        Long id,

        String title,

        String description,

        Double price

) {
}
