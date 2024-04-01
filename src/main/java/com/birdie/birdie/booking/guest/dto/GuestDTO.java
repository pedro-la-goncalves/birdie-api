package com.birdie.birdie.booking.guest.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.NotNull;

public record GuestDTO(

        @NotNull
        Long id,

        String name,

        String surname,

        @JsonAlias(value = "social_name")
        String socialName

) {
}
