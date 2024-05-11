package com.birdie.api.booking.reservation.checkout.dto;

import com.birdie.api.booking.reservation.Reservation;
import com.birdie.api.booking.reservation.accommodation.dto.AccommodationDTO;
import com.birdie.api.booking.reservation.guest.dto.GuestDTO;

public record CheckedOutReservationDTO(

        Long id,

        String entry,

        String departure,

        String checkIn,

        String checkOut,

        AccommodationDTO accommodation,

        GuestDTO guest

) {
    public CheckedOutReservationDTO(Reservation reservation) {
        this(
                reservation.getId(),
                String.valueOf(reservation.getArrival()),
                String.valueOf(reservation.getDeparture()),
                String.valueOf(reservation.getCheckIn()),
                String.valueOf(reservation.getCheckOut()),
                new AccommodationDTO(reservation.getAccommodation()),
                new GuestDTO(reservation.getGuest())
        );
    }
}
