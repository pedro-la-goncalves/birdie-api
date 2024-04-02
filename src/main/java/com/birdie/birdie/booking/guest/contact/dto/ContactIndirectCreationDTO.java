package com.birdie.birdie.booking.guest.contact.dto;

import com.birdie.birdie.booking.guest.contact.EContactType;
import com.birdie.birdie.booking.guest.dto.GuestDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ContactIndirectCreationDTO(

    @NotNull
    EContactType type,

    @NotBlank
    String value,

    @Valid
    GuestDTO guest

) {
}
