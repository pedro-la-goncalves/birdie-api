package com.birdie.birdie.dto;

import com.birdie.birdie.model.Guest;

public record GuestDTO(
        long id,
        String name,
        String document,
        String phone
) {
    public GuestDTO(Guest guest) {
        this(guest.getId(), guest.getName(), guest.getDocument(), guest.getPhone());
    }
}
