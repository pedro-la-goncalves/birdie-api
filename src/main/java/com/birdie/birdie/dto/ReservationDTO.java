package com.birdie.birdie.dto;

import com.birdie.birdie.model.Reservation;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ReservationDTO(
        long id,
        GuestDTO guest,
        String scheduledEntry,
        String scheduledDeparture,
        String checkIn,
        String checkOut,
        boolean parking,
        double estimatedTotal,
        double totalCharged
) {
    public ReservationDTO(Reservation reservation) {
        this(
                reservation.getId(),
                new GuestDTO(reservation.getGuest()),
                String.valueOf(reservation.getScheduledEntry()),
                String.valueOf(reservation.getScheduledDeparture()),
                String.valueOf(reservation.getCheckIn()),
                String.valueOf(reservation.getCheckOut()),
                reservation.isParking(),
                reservation.getEstimatedTotal(),
                reservation.getTotalCharged()
        );
    }
}
