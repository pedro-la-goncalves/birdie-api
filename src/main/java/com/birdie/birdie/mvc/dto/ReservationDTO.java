package com.birdie.birdie.mvc.dto;

import com.birdie.birdie.mvc.model.Reservation;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

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
        double totalCharged,

        List<TotalChargedDetailDTO> totalChargedDetails
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
                reservation.getTotalCharged(),
                null
        );
    }
    public ReservationDTO(Reservation reservation, List<TotalChargedDetailDTO> totalChargedDetails) {
        this(
                reservation.getId(),
                new GuestDTO(reservation.getGuest()),
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
