package com.birdie.birdie.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "reservation")
@Data
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="entry")
    private LocalDate entry;

    @Column(name="exit")
    private LocalDate exit;

    @Column(name="check_in")
    private LocalDateTime checkIn;

    @Column(name="check_out")
    private LocalDateTime checkOut;

    @Column(name="parking")
    private boolean parking;

    @Column(name="total")
    private double estimatedTotal;

    @Column(name="total")
    private double totalCharged;
}
