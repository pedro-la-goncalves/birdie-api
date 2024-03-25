package com.birdie.birdie.model;

import com.birdie.birdie.dto.CreateGuestDTO;
import com.birdie.birdie.dto.GuestDTO;
import com.birdie.birdie.dto.UpdateGuestDTO;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "guest")
@Data
@NoArgsConstructor
public class Guest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "document", nullable = false)
    private String document;

    @Column(name = "phone", nullable = false)
    private String phone;

//    @Column(name = "deleted_at")
//    private LocalDateTime deletedAt;

    @OneToMany(mappedBy = "guest", cascade = CascadeType.ALL)
    private List<Reservation> reservations;

    public Guest(GuestDTO guestDTO) {
        this.id = guestDTO.id();
        this.name = guestDTO.name();
        this.document = guestDTO.document();
        this.phone = guestDTO.phone();
    }

    public Guest(CreateGuestDTO createGuestDTO) {
        this.name = createGuestDTO.name();
        this.document = createGuestDTO.document();
        this.phone = createGuestDTO.phone();
    }

    public Guest update(UpdateGuestDTO updateGuestDTO) {
        if (updateGuestDTO.name() != null) this.name = updateGuestDTO.name();
        if (updateGuestDTO.document() != null) this.document = updateGuestDTO.document();
        if (updateGuestDTO.phone() != null) this.phone = updateGuestDTO.phone();

        return this;
    }

    public void softDelete() {
//        this.deletedAt = LocalDateTime.now();
    }
}
