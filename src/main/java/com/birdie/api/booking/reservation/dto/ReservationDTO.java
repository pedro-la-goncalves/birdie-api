package com.birdie.api.booking.reservation.dto;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record ReservationDTO(

        @NotNull
        Long id,

        LocalDate entry,

        LocalDate departure

) {
}
