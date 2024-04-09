package com.birdie.api.booking.reservation.guest.dto;

import com.birdie.api.booking.reservation.guest.Guest;
import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record GuestDTO(

        @NotNull
        Long id,

        String name,

        String surname,

        @JsonAlias(value = "social_name")
        String socialName,

        String birthdate,

        List<GuestContactDTO> contacts

) {
        public GuestDTO(Guest guest) {
                this(
                        guest.getId(),
                        guest.getName(),
                        guest.getSurname(),
                        guest.getSocialName(),
                        guest.getBirthdate().toString(),
                        guest.getContacts().stream().map(contact -> new GuestContactDTO(contact.getType(), contact.getValue())).toList()
                );
        }
}
