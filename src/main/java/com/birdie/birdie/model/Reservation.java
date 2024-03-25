package com.birdie.birdie.model;

import com.birdie.birdie.dto.CreateReservationDTO;
import com.birdie.birdie.dto.UpdateReservationDTO;
import jakarta.persistence.*;
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
    private LocalDate scheduledEntry;

    @Column(name="scheduled_departure")
    private LocalDate scheduledDeparture;

    @Column(name="check_in")
    private LocalDateTime checkIn;

    @Column(name="check_out")
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

    public Reservation(CreateReservationDTO createReservationDTO) {
        this.scheduledEntry = LocalDate.parse(createReservationDTO.scheduledEntry());
        this.scheduledDeparture = LocalDate.parse(createReservationDTO.scheduledDeparture());
        this.parking = createReservationDTO.parking();
        this.guest = new Guest(createReservationDTO.guest());
    }

    public Reservation update(UpdateReservationDTO updateReservationDTO) {
        if (updateReservationDTO.scheduledEntry() != null) this.scheduledEntry = LocalDate.parse(updateReservationDTO.scheduledEntry());
        if (updateReservationDTO.scheduledDeparture() != null) this.scheduledDeparture = LocalDate.parse(updateReservationDTO.scheduledDeparture());
        if (updateReservationDTO.scheduledDeparture() != null) this.scheduledDeparture = LocalDate.parse(updateReservationDTO.scheduledDeparture());

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
