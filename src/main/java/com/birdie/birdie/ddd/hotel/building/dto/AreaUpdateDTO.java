package com.birdie.birdie.ddd.hotel.building.dto;

import com.birdie.birdie.ddd.hotel.building.EAccess;
import jakarta.validation.constraints.NotNull;

public record AreaUpdateDTO(

        @NotNull
        Long id,

        String title,

        String description,

        EAccess access

) {
}
