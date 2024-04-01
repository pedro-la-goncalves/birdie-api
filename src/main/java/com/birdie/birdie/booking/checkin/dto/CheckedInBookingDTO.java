package com.birdie.birdie.booking.checkin.dto;

import com.birdie.birdie.booking.Booking;
import com.birdie.birdie.booking.accommodation.Accommodation;
import com.birdie.birdie.booking.guest.Guest;
import com.fasterxml.jackson.annotation.JsonAlias;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record CheckedInBookingDTO(

        Long id,

        LocalDate entry,

        LocalDate departure,

        @JsonAlias(value = "check_in")
        LocalDateTime checkIn,

        Accommodation accommodation,

        Guest guest

) {
    public CheckedInBookingDTO(Booking booking) {
        this(
                booking.getId(),
                booking.getEntry(),
                booking.getDeparture(),
                booking.getCheckIn(),
                booking.getAccommodation(),
                booking.getGuest()
        );
    }
}
