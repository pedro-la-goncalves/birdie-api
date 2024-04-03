package com.birdie.birdie.booking.guest.dto;

import com.birdie.birdie.booking.guest.Guest;
import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;

public record GuestDTO(

        @NotNull
        Long id,

        String name,

        String surname,

        @JsonAlias(value = "social_name")
        String socialName,

        LocalDate birthdate,

        List<GuestContactDTO> contacts

) {
        public GuestDTO(Guest guest) {
                this(
                        guest.getId(),
                        guest.getName(),
                        guest.getSurname(),
                        guest.getSocialName(),
                        guest.getBirthdate(),
                        guest.getContacts().stream().map(contact -> new GuestContactDTO(contact.getType(), contact.getValue())).toList()
                );
        }
}
