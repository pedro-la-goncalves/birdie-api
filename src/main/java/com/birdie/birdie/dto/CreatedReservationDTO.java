package com.birdie.birdie.dto;

import com.birdie.birdie.model.Guest;
import com.birdie.birdie.model.Reservation;

public record CreatedReservationDTO(
        long id,
//        long guestId,
        GuestDTO guest,
        String scheduledEntry,
        String scheduledDeparture,
        boolean parking,
        double estimatedTotal
) {
    public CreatedReservationDTO(Reservation reservation) {
        this(
                reservation.getId(),
                new GuestDTO(reservation.getGuest()),
                String.valueOf(reservation.getScheduledEntry()),
                String.valueOf(reservation.getScheduledDeparture()),
                reservation.isParking(),
                reservation.getEstimatedTotal()
       );
    }
}
