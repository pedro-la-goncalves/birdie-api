package com.birdie.api.booking.reservation.dto;

import com.birdie.api.booking.reservation.guest.Guest;
import com.birdie.api.booking.reservation.accommodation.Accommodation;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.Valid;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record ReservationCreationDTO(

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
