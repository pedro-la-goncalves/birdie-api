package com.birdie.birdie.booking.guest.contact.dto;

import com.birdie.birdie.booking.guest.contact.Contact;
import com.birdie.birdie.booking.guest.contact.ContactType;
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
