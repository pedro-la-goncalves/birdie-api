package com.birdie.birdie.dto;

import com.birdie.birdie.model.Guest;
import com.fasterxml.jackson.annotation.JsonAlias;

public record GuestDTO(
        @JsonAlias("id") long id,
        @JsonAlias("name") String name,
        @JsonAlias("document") String document,
        @JsonAlias("phone") String phone
) {
    public GuestDTO(Guest guest) {
        this(guest.getId(), guest.getName(), guest.getDocument(), guest.getPhone());
    }
}
