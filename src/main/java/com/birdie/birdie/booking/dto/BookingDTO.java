package com.birdie.birdie.booking.dto;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record BookingDTO(

        @NotNull
        Long id,

        LocalDate entry,

        LocalDate departure

) {
}
