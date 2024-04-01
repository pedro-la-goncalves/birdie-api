package com.birdie.birdie.ddd.hotel.building;

import com.birdie.birdie.ddd.hotel.booking.Booking;
import com.birdie.birdie.ddd.hotel.booking.dto.BookingCreationDTO;
import com.birdie.birdie.ddd.hotel.booking.dto.BookingUpdateDTO;
import com.birdie.birdie.ddd.hotel.building.dto.AreaCreationDTO;
import com.birdie.birdie.ddd.hotel.building.dto.AreaUpdateDTO;
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

    public Area(AreaCreationDTO area) {
        this.title = area.title();
        this.description = area.description();
        this.access = area.access();
    }

    public Area update(AreaUpdateDTO area) {
        if (area.title() != null) this.title = area.title();
        if (area.description() != null) this.description = area.description();
        if (area.access() != null) this.access = area.access();

        return this;
    }

}
