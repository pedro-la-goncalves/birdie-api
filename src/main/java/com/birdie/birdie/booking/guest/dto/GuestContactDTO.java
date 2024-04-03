package com.birdie.birdie.booking.guest.dto;

import com.birdie.birdie.booking.guest.contact.EContactType;

public record GuestContactDTO(

        EContactType type,

        String value

) {
}
