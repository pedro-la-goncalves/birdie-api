package com.birdie.api.booking.reservation.accommodation;

import com.birdie.api.booking.reservation.Reservation;
import com.birdie.api.booking.reservation.accommodation.dto.AccommodationCreationDTO;
import com.birdie.api.booking.reservation.accommodation.dto.AccommodationDTO;
import com.birdie.api.booking.reservation.accommodation.dto.AccommodationUpdateDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
public class Accommodation {

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
    private Double price;

    @OneToMany(mappedBy = "accommodation", cascade = CascadeType.ALL)
    private List<Reservation> reservations;

    public Accommodation(AccommodationDTO accommodation) {
        this.id = accommodation.id();
        this.title = accommodation.title();
        this.description = accommodation.description();
        this.price = accommodation.price();
    }

    public Accommodation(AccommodationCreationDTO accommodation) {
        this.title = accommodation.title();
        this.description = accommodation.description();
        this.price = accommodation.price();
    }

    public Accommodation update(AccommodationUpdateDTO accommodation) {
        if (accommodation.title() != null) this.title = accommodation.title();
        if (accommodation.description() != null) this.description = accommodation.description();
        if (accommodation.price() != null) this.price = accommodation.price();

        return this;
    }

}
