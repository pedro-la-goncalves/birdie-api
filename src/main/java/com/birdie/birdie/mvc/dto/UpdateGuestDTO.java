package com.birdie.birdie.mvc.dto;

import jakarta.validation.constraints.NotNull;

public record UpdateGuestDTO(
        @NotNull long id,
        String name,
        String document,
        String phone
) {}
