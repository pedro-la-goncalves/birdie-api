package com.birdie.birdie.dto;

import com.birdie.birdie.model.Reservation;

import java.util.List;

public record CheckedInReservationDTO(
        long id,
        long guestId,
        String scheduledEntry,
        String scheduledDeparture,
        String checkIn,
        boolean parking
) {
    public CheckedInReservationDTO(Reservation reservation) {
        this(
                reservation.getId(),
                reservation.getGuest().getId(),
                String.valueOf(reservation.getScheduledEntry()),
                String.valueOf(reservation.getScheduledDeparture()),
                String.valueOf(reservation.getCheckIn()),
                reservation.isParking()
        );
    }
}
