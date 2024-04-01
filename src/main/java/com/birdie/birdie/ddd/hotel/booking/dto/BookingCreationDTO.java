package com.birdie.birdie.ddd.hotel.booking.dto;

import com.birdie.birdie.ddd.hotel.booking.guest.dto.GuestDTO;
import com.birdie.birdie.ddd.hotel.booking.room.dto.RoomDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.Valid;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record BookingCreationDTO(

        @NotNull
        Long id,

        @JsonFormat(pattern = "yyyy-MM-dd")
        @FutureOrPresent
        LocalDate entry,

        @JsonFormat(pattern = "yyyy-MM-dd")
        @FutureOrPresent
        LocalDate departure,

        @Valid
        GuestDTO guest,

        @Valid
        RoomDTO room

) {
}
