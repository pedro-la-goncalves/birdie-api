package com.birdie.birdie.booking.guest.contact.dto;

import com.birdie.birdie.booking.guest.contact.ContactType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotNull;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ContactUpdateDTO(

    @NotNull
    Long id,

    ContactType type,

    String value

) {
}
