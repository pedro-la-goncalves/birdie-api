package com.birdie.api.booking.reservation.checkout.dto;

import com.birdie.api.booking.reservation.Reservation;
import com.birdie.api.booking.reservation.guest.Guest;
import com.birdie.api.booking.reservation.accommodation.Accommodation;
import com.fasterxml.jackson.annotation.JsonAlias;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record CheckedOutReservationDTO(

        Long id,

        LocalDate entry,

        LocalDate departure,

        @JsonAlias(value = "check_in")
        LocalDateTime checkIn,

        @JsonAlias(value = "check_out")
        LocalDateTime checkOut,

        Accommodation accommodation,

        Guest guest

) {
    public CheckedOutReservationDTO(Reservation reservation) {
        this(
                reservation.getId(),
                reservation.getEntry(),
                reservation.getDeparture(),
                reservation.getCheckIn(),
                reservation.getCheckOut(),
                reservation.getAccommodation(),
                reservation.getGuest()
        );
    }
}
