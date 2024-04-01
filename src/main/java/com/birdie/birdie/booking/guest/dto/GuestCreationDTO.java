package com.birdie.birdie.booking.guest.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record GuestCreationDTO(

        @NotNull
        Long id,

        @NotBlank
        String name,

        String surname,

        String socialName

) {
}
