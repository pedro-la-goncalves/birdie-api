package com.birdie.birdie.ddd.hotel.building;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Area {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @NotBlank
    private String title;

    @Column
    @NotBlank
    private String description;

    @Column
    @Enumerated(EnumType.STRING)
    private EAccess access;

}
