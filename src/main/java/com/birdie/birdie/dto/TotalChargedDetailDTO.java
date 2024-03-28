package com.birdie.birdie.dto;

public record TotalChargedDetailDTO(
        String type,
        double unitValue,
        double multiplier,
        double totalValue
) {}
