package com.birdie.birdie.ddd.hotel.booking.dto;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record BookingDTO(

        @NotNull
        Long id,

        LocalDate entry,

        LocalDate departure

) {
}
