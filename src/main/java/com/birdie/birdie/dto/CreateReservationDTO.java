package com.birdie.birdie.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.Valid;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public record CreateReservationDTO(
        @Valid
        GuestDTO guest,
        @JsonAlias(value = "scheduled_entry")
        @JsonFormat(pattern = "yyyy-MM-dd")
        @FutureOrPresent
        LocalDate scheduledEntry,
        @JsonAlias(value = "scheduled_departure")
        @JsonFormat(pattern = "yyyy-MM-dd")
        @FutureOrPresent
        LocalDate scheduledDeparture,
        @NotNull
        boolean parking
) {
}
