package com.birdie.api.booking.reservation.guest.contact.dto;

import com.birdie.api.booking.reservation.guest.contact.Contact;
import com.birdie.api.booking.reservation.guest.contact.ContactType;
import jakarta.validation.constraints.NotNull;

public record ContactDTO(

        @NotNull
        Long id,

        ContactType type,

        String value

) {

        public ContactDTO(Contact contact) {
                this(
                        contact.getId(),
                        contact.getType(),
                        contact.getValue()
                );
        }

}
