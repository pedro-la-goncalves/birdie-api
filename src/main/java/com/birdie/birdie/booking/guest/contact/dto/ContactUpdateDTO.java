package com.birdie.birdie.booking.guest.contact.dto;

import com.birdie.birdie.booking.guest.contact.EContactType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotNull;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ContactUpdateDTO(

    @NotNull
    Long id,

    EContactType type,

    String value

) {
}
