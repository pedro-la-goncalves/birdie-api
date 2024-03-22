package com.birdie.birdie.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "guest")
@Data
public class Guest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="name")
    private String name;

    @Column(name="document")
    private String document;
}
