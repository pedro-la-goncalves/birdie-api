package com.birdie.birdie.model;

import com.birdie.birdie.dto.CreateReservationDTO;
import com.birdie.birdie.dto.UpdateReservationDTO;
import com.birdie.birdie.enums.EDefaultHours;
import jakarta.persistence.*;
import jakarta.validation.constraints.AssertFalse;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "reservation")
@Data
@NoArgsConstructor
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="scheduled_entry")
    @FutureOrPresent
    private LocalDate scheduledEntry;

    @Column(name="scheduled_departure")
    @FutureOrPresent
    private LocalDate scheduledDeparture;

    @Column(name="check_in")
    @PastOrPresent
    private LocalDateTime checkIn;

    @Column(name="check_out")
    @PastOrPresent
    private LocalDateTime checkOut;

    @Column(name="parking")
    private boolean parking;

    @Column(name="estimated_total")
    private double estimatedTotal;

    @Column(name="total_charged")
    private double totalCharged;

//    @Column(name="deleted_at")
//    private LocalDateTime deletedAt;

    @ManyToOne()
    @JoinColumn(name = "guest_id")
    private Guest guest;

    @AssertFalse(message = "os campos 'scheduled_entry' e 'scheduled_departure' devem ser diferentes")
    private boolean isScheduledEntryAndScheduledDepartureEquals() {
        return this.scheduledEntry.isEqual(this.scheduledDeparture);
    }

    public Reservation(CreateReservationDTO createReservationDTO) {
        this.scheduledEntry = createReservationDTO.scheduledEntry();
        this.scheduledDeparture = createReservationDTO.scheduledDeparture();
        this.parking = createReservationDTO.parking();
        this.guest = new Guest(createReservationDTO.guest());
    }

    public Reservation update(UpdateReservationDTO updateReservationDTO) {
        if (updateReservationDTO.scheduledEntry() != null) this.scheduledEntry = updateReservationDTO.scheduledEntry();
        if (updateReservationDTO.scheduledDeparture() != null) this.scheduledDeparture = updateReservationDTO.scheduledDeparture();
        this.parking = updateReservationDTO.parking();

        return this;
    }

    public Reservation checkIn(LocalDateTime checkin) {
        this.checkIn = checkin;
        return this;
    }

    public Reservation checkOut(LocalDateTime checkOut) {
        this.checkOut = checkOut;
        return this;
    }



    public void softDelete() {
//        this.deletedAt = LocalDateTime.now();
    }
}
