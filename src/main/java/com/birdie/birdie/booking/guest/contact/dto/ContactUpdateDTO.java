package com.birdie.birdie.booking.guest.contact.dto;

import com.birdie.birdie.booking.guest.contact.EContactType;
import jakarta.validation.constraints.NotNull;

public record ContactUpdateDTO(

    @NotNull
    Long id,

    EContactType type,

    String value

) {
}
