package com.birdie.birdie.ddd.hotel.booking.guest.contact.dto;

import com.birdie.birdie.ddd.hotel.booking.guest.contact.EContactType;
import jakarta.validation.constraints.NotNull;

public record ContactUpdateDTO(

    @NotNull
    Long id,

    EContactType type,

    String value

) {
}
