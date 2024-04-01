package com.birdie.birdie.mvc.dto;

import com.birdie.birdie.mvc.model.Reservation;

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
