package com.birdie.api.booking.reservation.guest.contact.dto;

import com.birdie.api.booking.reservation.guest.contact.ContactType;
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
