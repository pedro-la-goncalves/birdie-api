package com.birdie.api.booking.reservation.guest.contact.dto;

import com.birdie.api.booking.reservation.guest.contact.annotation.ContactFields;
import com.birdie.api.booking.reservation.guest.contact.ContactType;
import com.birdie.api.booking.reservation.guest.dto.GuestDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@ContactFields
public record IndirectContactCreationDTO(
    @NotNull
    ContactType type,

    @NotBlank
    String value,

    @Valid
    GuestDTO guest
) {
}
