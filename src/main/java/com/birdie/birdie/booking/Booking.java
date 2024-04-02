package com.birdie.birdie.booking;

import com.birdie.birdie.booking.accommodation.Accommodation;
import com.birdie.birdie.booking.dto.BookingCreationDTO;
import com.birdie.birdie.booking.dto.BookingDTO;
import com.birdie.birdie.booking.dto.BookingUpdateDTO;
import com.birdie.birdie.booking.expenses.Expense;
import com.birdie.birdie.booking.guest.Guest;
import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.PastOrPresent;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
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

    @Column(name = "check_in")
    @PastOrPresent
    private LocalDateTime checkIn;

    @Column(name = "check_out")
    @PastOrPresent
    private LocalDateTime checkOut;

    @ManyToOne()
    @JoinColumn(name = "guest_id")
    private Guest guest;

    @ManyToOne()
    @JoinColumn(name = "guest_id")
    private Accommodation accommodation;

    @OneToMany(mappedBy = "booking", cascade = CascadeType.ALL)
    private List<Expense> expenses;

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

    public Booking setCheckIn(LocalDateTime checkIn) {
        this.checkIn = checkIn;

        return this;
    }

    public Booking setCheckOut(LocalDateTime checkOut) {
        this.checkOut = checkOut;

        return this;
    }
}
