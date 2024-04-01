package com.birdie.birdie.ddd.hotel.booking.guest;

import com.birdie.birdie.ddd.hotel.booking.Booking;
import com.birdie.birdie.ddd.hotel.booking.guest.contact.Contact;
import com.birdie.birdie.ddd.hotel.booking.guest.dto.GuestDTO;
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
    private List<Contact> contacts;

    @OneToMany(mappedBy = "guest", cascade = CascadeType.ALL)
    private List<Booking> bookings;

    public Guest(GuestDTO guest) {
        this.id = guest.id();
        if (guest.name() != null) this.name = guest.name();
        if (guest.surname() != null) this.surname = guest.surname();
        if (guest.socialName() != null) this.socialName = guest.socialName();
    }

}
