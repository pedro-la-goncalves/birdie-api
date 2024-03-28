package com.birdie.birdie.dto;

import com.birdie.birdie.model.Reservation;
import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateReservationDTO(
        @NotNull @Valid GuestDTO guest,
        @NotBlank String scheduledEntry,
        @NotBlank String scheduledDeparture,
        @NotNull boolean parking
) {
}
