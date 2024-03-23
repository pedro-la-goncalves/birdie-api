package com.birdie.birdie.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "reservation")
@Data
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

    @ManyToOne()
    @JoinColumn(name = "guest_id")
    private Guest guest;
}
