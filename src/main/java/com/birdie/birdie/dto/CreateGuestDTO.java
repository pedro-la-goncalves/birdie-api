package com.birdie.birdie.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;

@JsonIgnoreProperties(ignoreUnknown = true)
public record CreateGuestDTO(
        @NotBlank String name,
        @NotBlank String document,
        @NotBlank String phone
) {}
