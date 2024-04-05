package com.birdie.birdie.booking.guest.dto;

import com.birdie.birdie.booking.guest.contact.ContactType;

public record GuestContactDTO(

        ContactType type,

        String value

) {
}
