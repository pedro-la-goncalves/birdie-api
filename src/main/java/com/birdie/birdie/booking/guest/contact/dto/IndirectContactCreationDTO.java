package com.birdie.birdie.booking.guest.contact.dto;

import com.birdie.birdie.booking.guest.contact.ContactType;
import com.birdie.birdie.booking.guest.dto.GuestDTO;
import com.birdie.birdie.booking.guest.contact.annotation.ContactFields;
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
