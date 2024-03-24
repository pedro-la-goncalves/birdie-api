package com.birdie.birdie.dto;

import com.birdie.birdie.model.Guest;
import com.fasterxml.jackson.annotation.JsonAlias;

import java.util.Objects;

public record UpdateGuestDTO(
        @JsonAlias("id") long id,
        @JsonAlias("name") String name,
        @JsonAlias("document") String document,
        @JsonAlias("phone") String phone
) {
    public UpdateGuestDTO(Guest guest) {
        this(guest.getId(), guest.getName(), guest.getDocument(), guest.getPhone());
    }

    public Guest toGuest() {
        Guest guest = new Guest();
        guest.setId(this.id);
        if (!Objects.isNull(this.name)) guest.setName(this.name);
        if (!Objects.isNull(this.document)) guest.setDocument(this.document);
        if (!Objects.isNull(this.phone)) guest.setPhone(this.phone);

        return guest;
    }
}
