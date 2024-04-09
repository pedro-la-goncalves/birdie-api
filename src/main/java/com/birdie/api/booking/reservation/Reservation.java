package com.birdie.api.booking.reservation;

import com.birdie.api.booking.reservation.dto.ReservationCreationDTO;
import com.birdie.api.booking.reservation.dto.ReservationDTO;
import com.birdie.api.booking.reservation.dto.ReservationUpdateDTO;
import com.birdie.api.booking.reservation.expense.Expense;
import com.birdie.api.booking.reservation.guest.Guest;
import com.birdie.api.booking.reservation.accommodation.Accommodation;
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
public class Reservation {

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
    @JoinColumn(name = "accommodation_id")
    private Accommodation accommodation;

    @OneToMany(mappedBy = "booking", cascade = CascadeType.ALL)
    private List<Expense> expenses;

    public Reservation(ReservationDTO booking) {
        this.id = booking.id();
        this.entry = booking.entry();
        this.departure = booking.departure();
    }

    public Reservation(ReservationCreationDTO booking) {
        this.entry = booking.entry();
        this.departure = booking.departure();
        this.guest = booking.guest();
        this.accommodation = booking.accommodation();
    }

    public Reservation update(ReservationUpdateDTO booking) {
        if (booking.entry() != null) this.entry = booking.entry();
        if (booking.departure() != null) this.departure = booking.departure();
        if (booking.guest() != null) this.guest = new Guest(booking.guest());
        if (booking.accommodation() != null) this.accommodation = new Accommodation(booking.accommodation());

        return this;
    }

    public Reservation setCheckIn(LocalDateTime checkIn) {
        this.checkIn = checkIn;

        return this;
    }

    public Reservation setCheckOut(LocalDateTime checkOut) {
        this.checkOut = checkOut;

        return this;
    }
}
