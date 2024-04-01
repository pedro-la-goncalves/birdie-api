package com.birdie.birdie.ddd.hotel.booking;

import com.birdie.birdie.ddd.hotel.booking.accommodation.Accommodation;
import com.birdie.birdie.ddd.hotel.booking.dto.BookingCreationDTO;
import com.birdie.birdie.ddd.hotel.booking.dto.BookingDTO;
import com.birdie.birdie.ddd.hotel.booking.dto.BookingUpdateDTO;
import com.birdie.birdie.ddd.hotel.booking.guest.Guest;
import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;

import java.time.LocalDate;

@Entity
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @FutureOrPresent
    private LocalDate entry;

    @Column
    @FutureOrPresent
    private LocalDate departure;

    @ManyToOne()
    @JoinColumn(name = "guest_id")
    private Guest guest;

    @ManyToOne()
    @JoinColumn(name = "guest_id")
    private Accommodation accommodation;

    public Booking(BookingDTO booking) {
        this.id = booking.id();
        this.entry = booking.entry();
        this.departure = booking.departure();
    }

    public Booking(BookingCreationDTO booking) {
        this.entry = booking.entry();
        this.departure = booking.departure();
        this.guest = booking.guest();
        this.accommodation = booking.accommodation();
    }

    public Booking update(BookingUpdateDTO booking) {
        if (booking.entry() != null) this.entry = booking.entry();
        if (booking.departure() != null) this.departure = booking.departure();
        if (booking.guest() != null) this.guest = new Guest(booking.guest());
        if (booking.accommodation() != null) this.accommodation = new Accommodation(booking.accommodation());

        return this;
    }
}
