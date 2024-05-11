package com.birdie.api.booking.reservation.checkin.dto;

import com.birdie.api.booking.reservation.Reservation;
import com.birdie.api.booking.reservation.accommodation.dto.AccommodationDTO;
import com.birdie.api.booking.reservation.guest.dto.GuestDTO;
import com.fasterxml.jackson.annotation.JsonAlias;

public record CheckedInReservationDTO(

        Long id,

        String entry,

        String departure,

        @JsonAlias(value = "check_in")
        String checkIn,

        AccommodationDTO accommodation,

        GuestDTO guest

) {
    public CheckedInReservationDTO(Reservation reservation) {
        this(
                reservation.getId(),
                String.valueOf(reservation.getArrival()),
                String.valueOf(reservation.getDeparture()),
                String.valueOf(reservation.getCheckIn()),
                new AccommodationDTO(reservation.getAccommodation()),
                new GuestDTO(reservation.getGuest())
        );
    }
}
