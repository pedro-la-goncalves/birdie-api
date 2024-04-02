package com.birdie.birdie.booking.checkout.dto;

import com.birdie.birdie.booking.Booking;
import com.birdie.birdie.booking.accommodation.Accommodation;
import com.birdie.birdie.booking.guest.Guest;
import com.fasterxml.jackson.annotation.JsonAlias;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record CheckedOutBookingDTO(

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
    public CheckedOutBookingDTO(Booking booking) {
        this(
                booking.getId(),
                booking.getEntry(),
                booking.getDeparture(),
                booking.getCheckIn(),
                booking.getCheckOut(),
                booking.getAccommodation(),
                booking.getGuest()
        );
    }
}
