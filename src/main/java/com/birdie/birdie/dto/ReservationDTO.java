package com.birdie.birdie.dto;

import com.birdie.birdie.model.Reservation;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ReservationDTO(
        @JsonAlias("id") long id,
        @JsonAlias("guest_id") long guestId,
        @JsonAlias("scheduled_entry") String scheduledEntry,
        @JsonAlias("scheduled_departure") String scheduledDeparture,
        @JsonAlias("check_in") String checkIn,
        @JsonAlias("check_out") String checkOut,
        @JsonAlias("parking") boolean parking,
        @JsonAlias("estimated_total") double estimatedTotal,
        @JsonAlias("total_charged") double totalCharged
) {
    public ReservationDTO(Reservation reservation) {
        this(
                reservation.getId(),
                reservation.getGuest().getId(),
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
