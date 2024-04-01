package com.birdie.birdie.ddd.hotel.booking.dto;

import com.birdie.birdie.ddd.hotel.booking.accommodation.Accommodation;
import com.birdie.birdie.ddd.hotel.booking.guest.Guest;
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
