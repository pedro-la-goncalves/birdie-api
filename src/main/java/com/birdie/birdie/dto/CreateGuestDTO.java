package com.birdie.birdie.dto;

import com.birdie.birdie.model.Guest;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record CreateGuestDTO(
        @JsonAlias("name") String name,
        @JsonAlias("document") String document,
        @JsonAlias("phone") String phone
) {
    public CreateGuestDTO(Guest guest) {
        this(guest.getName(), guest.getDocument(), guest.getPhone());
    }

    public Guest toGuest() {
        Guest guest = new Guest();
        guest.setName(this.name);
        guest.setDocument(this.document);
        guest.setPhone(this.phone);

        return guest;
    }
}
