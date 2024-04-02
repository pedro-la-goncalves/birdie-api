package com.birdie.birdie.booking.guest.contact.dto;

import com.birdie.birdie.booking.guest.Guest;
import com.birdie.birdie.booking.guest.contact.EContactType;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ContactCreationDTO(

    @NotNull
    EContactType type,

    @NotBlank
    String value,

    @Valid
    Guest guest

) {
}
