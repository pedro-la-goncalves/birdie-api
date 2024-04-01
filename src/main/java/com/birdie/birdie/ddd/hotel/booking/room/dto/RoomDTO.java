package com.birdie.birdie.ddd.hotel.booking.room.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RoomDTO(

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
