package com.birdie.birdie.dto;

import jakarta.validation.constraints.NotNull;

public record UpdateGuestDTO(
        @NotNull long id,
        String name,
        String document,
        String phone
) {}
