package com.birdie.birdie.booking.dto;

import com.birdie.birdie.booking.accommodation.dto.AccommodationDTO;
import com.birdie.birdie.booking.guest.dto.GuestDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record BookingUpdateDTO(

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