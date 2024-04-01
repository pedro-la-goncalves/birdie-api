package com.birdie.birdie.booking.dto;

import com.birdie.birdie.booking.guest.Guest;
import com.birdie.birdie.booking.accommodation.Accommodation;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.Valid;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record BookingCreationDTO(

        @NotNull
        Long id,

        @JsonFormat(pattern = "yyyy-MM-dd")
        @FutureOrPresent
        LocalDate entry,

        @JsonFormat(pattern = "yyyy-MM-dd")
        @FutureOrPresent
        LocalDate departure,

        @Valid
        Guest guest,

        @Valid
        Accommodation accommodation

) {
}
