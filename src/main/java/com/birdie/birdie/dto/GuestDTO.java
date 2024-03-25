package com.birdie.birdie.dto;

import com.birdie.birdie.model.Guest;
import jakarta.validation.constraints.NotNull;

public record GuestDTO(
        @NotNull long id,
        String name,
        String document,
        String phone
) {
    public GuestDTO(Guest guest) {
        this(guest.getId(), guest.getName(), guest.getDocument(), guest.getPhone());
    }
}
