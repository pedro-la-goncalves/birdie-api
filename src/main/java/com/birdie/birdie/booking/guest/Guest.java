package com.birdie.birdie.booking.guest;

import com.birdie.birdie.booking.Booking;
import com.birdie.birdie.booking.guest.contact.Contact;
import com.birdie.birdie.booking.guest.dto.GuestCreationDTO;
import com.birdie.birdie.booking.guest.dto.GuestDTO;
import com.birdie.birdie.booking.guest.dto.GuestUpdateDTO;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
public class Guest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String surname;

    @Column
    private String socialName;

    @OneToMany(mappedBy = "guest", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<Contact> contacts;

    @OneToMany(mappedBy = "guest", cascade = CascadeType.ALL)
    private List<Booking> bookings;

    public Guest(GuestDTO guest) {
        this.id = guest.id();
        if (guest.name() != null) this.name = guest.name();
        if (guest.surname() != null) this.surname = guest.surname();
        if (guest.socialName() != null) this.socialName = guest.socialName();
    }

    public Guest(GuestCreationDTO guest) {
        this.name = guest.getName();
        if (guest.getSurname() != null) this.surname = guest.getSurname();
        if (guest.getSocialName() != null) this.socialName = guest.getSocialName();
    }

    public Guest update(GuestUpdateDTO guest) {
        if (guest.name() != null) this.name = guest.name();
        if (guest.surname() != null) this.surname = guest.surname();
        if (guest.socialName() != null) this.socialName = guest.socialName();

        return this;
    }

}
