package com.birdie.api.booking.reservation.dto;

import com.birdie.api.booking.reservation.accommodation.dto.AccommodationDTO;
import com.birdie.api.booking.reservation.guest.dto.GuestDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record ReservationUpdateDTO(

    @NotNull
    Long id,

    @JsonFormat(pattern = "yyyy-MM-dd")
    LocalDate entry,

    @JsonFormat(pattern = "yyyy-MM-dd")
    LocalDate departure,

    @Valid
    GuestDTO guest,

    @Valid
    AccommodationDTO accommodation

) {
}
