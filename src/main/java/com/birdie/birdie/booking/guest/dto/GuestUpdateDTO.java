package com.birdie.birdie.booking.guest.dto;

import jakarta.validation.constraints.NotNull;

public record GuestUpdateDTO(

        @NotNull
        Long id,

        String name,

        String surname,

        String socialName

) {
}
