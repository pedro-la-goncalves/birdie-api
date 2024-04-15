package com.birdie.api.booking.reservation.checkin.dto;

import com.birdie.api.booking.reservation.Reservation;
import com.birdie.api.booking.reservation.guest.Guest;
import com.birdie.api.booking.reservation.accommodation.Accommodation;
import com.fasterxml.jackson.annotation.JsonAlias;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record CheckedInReservationDTO(

        Long id,

        LocalDate entry,

        LocalDate departure,

        @JsonAlias(value = "check_in")
        LocalDateTime checkIn,

        Accommodation accommodation,

        Guest guest

) {
    public CheckedInReservationDTO(Reservation reservation) {
        this(
                reservation.getId(),
                reservation.getArrival(),
                reservation.getDeparture(),
                reservation.getCheckIn(),
                reservation.getAccommodation(),
                reservation.getGuest()
        );
    }
}
