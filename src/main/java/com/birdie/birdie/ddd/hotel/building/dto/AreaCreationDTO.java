package com.birdie.birdie.ddd.hotel.building.dto;

import com.birdie.birdie.ddd.hotel.building.EAccess;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AreaCreationDTO(

        @NotNull
        Long id,

        @NotBlank
        String title,

        @NotBlank
        String description,

        @NotNull
        EAccess access

) {
}
