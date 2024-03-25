package com.birdie.birdie.dto;

import com.birdie.birdie.model.Reservation;

import java.util.List;

public record CheckedOutReservationDTO(
        long id,
        long guestId,
        String scheduledEntry,
        String scheduledDeparture,
        String checkIn,
        String checkOut,
        boolean parking,
        double estimatedTotal,
        double totalCharged,
        List<TotalChargedDetailDTO> totalChargedDetails
) {
    public CheckedOutReservationDTO(Reservation reservation, List<TotalChargedDetailDTO> totalChargedDetails) {
        this(
                reservation.getId(),
                reservation.getGuest().getId(),
                String.valueOf(reservation.getScheduledEntry()),
                String.valueOf(reservation.getScheduledDeparture()),
                String.valueOf(reservation.getCheckIn()),
                String.valueOf(reservation.getCheckOut()),
                reservation.isParking(),
                reservation.getEstimatedTotal(),
                reservation.getTotalCharged(),
                totalChargedDetails
        );
    }
}
