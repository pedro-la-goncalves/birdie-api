package com.birdie.birdie.mvc.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.Valid;
import jakarta.validation.constraints.AssertFalse;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;

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
        @AssertFalse(message = "os campos 'scheduled_entry' e 'scheduled_departure' devem ser diferentes")
        private boolean isScheduledEntryAndScheduledDepartureEquals() {
                return this.scheduledEntry.isEqual(this.scheduledDeparture);
        }
}
