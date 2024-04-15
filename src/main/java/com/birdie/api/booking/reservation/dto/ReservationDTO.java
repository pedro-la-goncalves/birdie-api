package com.birdie.api.booking.reservation.dto;

import com.birdie.api.booking.reservation.Reservation;
import com.birdie.api.booking.reservation.accommodation.dto.AccommodationDTO;
import com.birdie.api.booking.reservation.guest.dto.GuestDTO;
import jakarta.validation.constraints.NotNull;

import java.util.Objects;

public record ReservationDTO(

        @NotNull
        Long id,

        String arrival,

        String departure,

        String checkIn,

        String checkOut,

        GuestDTO guest,

        AccommodationDTO accommodation

) {
        public ReservationDTO(Reservation reservation) {
                this(
                        reservation.getId(),
                        reservation.getArrival().toString(),
                        reservation.getDeparture().toString(),
                        !Objects.isNull(reservation.getCheckIn()) ? reservation.getCheckIn().toString() : null,
                        !Objects.isNull(reservation.getCheckOut()) ? reservation.getCheckOut().toString() : null,
                        new GuestDTO(reservation.getGuest()),
                        new AccommodationDTO(reservation.getAccommodation())
                );
        }
}
