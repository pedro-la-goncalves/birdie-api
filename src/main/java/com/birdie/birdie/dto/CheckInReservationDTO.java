package com.birdie.birdie.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CheckInReservationDTO(
        @NotNull long id,
        @JsonAlias("check_in") @NotBlank String checkIn
) {}
