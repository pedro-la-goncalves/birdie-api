package com.birdie.birdie.booking.guest.contact;

import com.birdie.birdie.booking.guest.Guest;
import com.birdie.birdie.booking.guest.contact.dto.ContactCreationDTO;
import com.birdie.birdie.booking.guest.contact.dto.ContactDTO;
import com.birdie.birdie.booking.guest.contact.dto.ContactUpdateDTO;
import jakarta.persistence.*;

public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @Enumerated(EnumType.STRING)
    private EContactType type;

    @Column
    private String value;

    @ManyToOne
    @JoinColumn(name = "guest_id")
    private Guest guest;

    public Contact(ContactDTO contact) {
        this.id = contact.id();
        if (contact.type() != null) this.type = contact.type();
        if (contact.value() != null) this.value = contact.value();
    }

    public Contact(ContactCreationDTO contact) {
        this.type = contact.type();
        this.value = contact.value();
        this.guest = new Guest(contact.guest());
    }

    public Contact update(ContactUpdateDTO contact) {
        if (contact.type() != null) this.type = contact.type();
        if (contact.value() != null) this.value = contact.value();

        return this;
    }

}
