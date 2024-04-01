package com.birdie.birdie.mvc.dto;

import com.birdie.birdie.mvc.model.Guest;
import com.birdie.birdie.mvc.model.Reservation;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.Valid;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

@JsonIgnoreProperties(ignoreUnknown = true)
public record UpdateReservationDTO(
        @NotNull
        long id,
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
        boolean parking
) {
    public UpdateReservationDTO(Reservation reservation) {
        this(reservation.getId(), new GuestDTO(reservation.getGuest()), reservation.getScheduledEntry(), reservation.getScheduledDeparture(), reservation.isParking());
    }

    public Reservation toReservation(Guest guest) {
        Reservation reservation = new Reservation();
        reservation.setId(this.id);
        reservation.setGuest(guest);
        reservation.setScheduledEntry(this.scheduledEntry);
        reservation.setScheduledDeparture(this.scheduledDeparture);
        reservation.setParking(this.parking);

        return reservation;
    }
}
