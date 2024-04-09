package com.birdie.api.booking.reservation.guest.contact.dto;

import com.birdie.api.booking.reservation.guest.Guest;
import com.birdie.api.booking.reservation.guest.contact.ContactType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ContactCreationDTO(

    @NotNull
    ContactType type,

    @NotBlank
    String value,

    @Valid
    Guest guest

) {
}
