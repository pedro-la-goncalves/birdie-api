package com.birdie.birdie.dto;

import com.birdie.birdie.model.Guest;
import com.birdie.birdie.model.Reservation;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDate;

@JsonIgnoreProperties(ignoreUnknown = true)
public record UpdateReservationDTO(
        @JsonAlias("id") long id,
        @JsonAlias("guest") GuestDTO guest,
        @JsonAlias("scheduled_entry") String scheduledEntry,
        @JsonAlias("scheduled_departure") String scheduledDeparture,
        @JsonAlias("parking") boolean parking
) {
    public UpdateReservationDTO(Reservation reservation) {
        this(reservation.getId(), new GuestDTO(reservation.getGuest()), String.valueOf(reservation.getScheduledEntry()), String.valueOf(reservation.getScheduledDeparture()), reservation.isParking());
    }

    public Reservation toReservation(Guest guest) {
        Reservation reservation = new Reservation();
        reservation.setId(this.id);
        reservation.setGuest(guest);
        reservation.setScheduledEntry(LocalDate.parse(this.scheduledEntry));
        reservation.setScheduledDeparture(LocalDate.parse(this.scheduledDeparture));
        reservation.setParking(this.parking);

        return reservation;
    }
}
