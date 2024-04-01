package com.birdie.birdie.mvc.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CheckOutReservationDTO(
        @NotNull long id,
        @NotBlank @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm") @JsonAlias("check_out") String checkOut
) {}
