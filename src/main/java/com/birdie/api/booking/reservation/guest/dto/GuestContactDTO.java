package com.birdie.api.booking.reservation.guest.dto;

import com.birdie.api.booking.reservation.guest.contact.ContactType;

public record GuestContactDTO(

        ContactType type,

        String value

) {
}
