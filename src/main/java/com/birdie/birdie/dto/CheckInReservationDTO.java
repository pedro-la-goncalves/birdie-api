package com.birdie.birdie.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDateTime;

public record CheckInReservationDTO(
        @NotNull
        long id,
        @JsonAlias("check_in")
        @PastOrPresent
        LocalDateTime checkIn
) {}
