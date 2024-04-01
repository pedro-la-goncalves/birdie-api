package com.birdie.birdie.ddd.hotel.booking.room;

import com.birdie.birdie.ddd.hotel.booking.room.dto.RoomDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Room {

    @NotNull
    private Long id;

    @NotBlank
    private String title;

    @NotBlank
    private String description;

    @NotNull
    private Double price;

    public Room(RoomDTO room) {
        this.id = room.id();
        this.title = room.title();
        this.description = room.description();
        this.price = room.price();
    }

}
