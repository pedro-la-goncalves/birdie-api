package com.birdie.birdie.ddd.hotel.booking;

import com.birdie.birdie.ddd.hotel.booking.dto.BookingCreationDTO;
import com.birdie.birdie.ddd.hotel.booking.dto.BookingUpdateDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;

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

    @NotNull
    @Column(name = "guest_id")
    private Long guestId;

    @NotNull
    @Column(name = "room_id")
    private Long roomId;

    public Booking(BookingCreationDTO booking) {
        this.entry = booking.entry();
        this.departure = booking.departure();
        this.guestId = booking.guest().id();
        this.roomId = booking.room().id();
    }

    public Booking update(BookingUpdateDTO booking) {
        if (booking.entry() != null) this.entry = booking.entry();
        if (booking.departure() != null) this.departure = booking.departure();
        if (booking.guest() != null) this.guestId = booking.guest().id();
        if (booking.room() != null) this.roomId = booking.room().id();

        return this;
    }
}
