package com.birdie.api.booking.reservation.accommodation.dto;

import com.birdie.api.booking.reservation.accommodation.Accommodation;
import jakarta.validation.constraints.NotNull;

public record AccommodationDTO(

        @NotNull
        Long id,

        String title,

        String description,

        Double price

) {
        public AccommodationDTO(Accommodation accommodation) {
                this(accommodation.getId(), accommodation.getTitle(), accommodation.getDescription(), accommodation.getPrice());
        }
}
