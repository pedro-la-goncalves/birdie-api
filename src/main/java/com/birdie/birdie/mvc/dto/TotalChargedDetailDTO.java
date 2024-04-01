package com.birdie.birdie.mvc.dto;

public record TotalChargedDetailDTO(
        String type,
        double unitValue,
        double multiplier,
        double totalValue
) {}
