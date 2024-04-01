package com.birdie.birdie.ddd.hotel.booking.guest.contact.dto;

import com.birdie.birdie.ddd.hotel.booking.guest.contact.EContactType;
import com.birdie.birdie.ddd.hotel.booking.guest.dto.GuestDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ContactCreationDTO(

    @NotNull
    EContactType type,

    @NotBlank
    String value,

    @Valid
    GuestDTO guest

) {
}
