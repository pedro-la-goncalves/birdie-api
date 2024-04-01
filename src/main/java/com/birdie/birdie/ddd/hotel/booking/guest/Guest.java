package com.birdie.birdie.ddd.hotel.booking.guest;

import com.birdie.birdie.ddd.hotel.booking.guest.dto.GuestDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Guest {

    @NotNull
    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String surname;

    private String socialName;

    public Guest(GuestDTO guest) {
        this.id = guest.id();
        if (guest.name() != null) this.name = guest.name();
        if (guest.surname() != null) this.surname = guest.surname();
        if (guest.socialName() != null) this.socialName = guest.socialName();
    }

}
