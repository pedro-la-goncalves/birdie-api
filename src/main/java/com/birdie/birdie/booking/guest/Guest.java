package com.birdie.birdie.booking.guest;

import com.birdie.birdie.booking.Booking;
import com.birdie.birdie.booking.guest.contact.Contact;
import com.birdie.birdie.booking.guest.dto.GuestCreationDTO;
import com.birdie.birdie.booking.guest.dto.GuestDTO;
import com.birdie.birdie.booking.guest.dto.GuestUpdateDTO;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
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

    @Column
    private LocalDate birthdate;

    @OneToMany(mappedBy = "guest", cascade = CascadeType.ALL)
    private List<Contact> contacts;

    @OneToMany(mappedBy = "guest", cascade = CascadeType.ALL)
    private List<Booking> bookings;

    public Guest(GuestDTO guest) {
        this.id = guest.id();
        if (guest.name() != null) this.name = guest.name();
        if (guest.surname() != null) this.surname = guest.surname();
        if (guest.socialName() != null) this.socialName = guest.socialName();
        if (guest.birthdate() != null) this.birthdate = guest.birthdate();
    }

    public Guest(GuestCreationDTO guest) {
        this.name = guest.getName();
        if (guest.getSurname() != null) this.surname = guest.getSurname();
        if (guest.getSocialName() != null) this.socialName = guest.getSocialName();
        if (guest.getBirthdate() != null) this.birthdate = guest.getBirthdate();
        if (guest.getContacts() != null) this.contacts = guest.getContacts().stream().map(contact -> new Contact(contact.type(), contact.value(), this)).toList();
    }

    public Guest update(GuestUpdateDTO guest) {
        if (guest.name() != null) this.name = guest.name();
        if (guest.surname() != null) this.surname = guest.surname();
        if (guest.socialName() != null) this.socialName = guest.socialName();
        if (guest.birthdate() != null) this.birthdate = guest.birthdate();

        return this;
    }

}
