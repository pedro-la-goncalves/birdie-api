package com.birdie.birdie.booking.expenses.dto;

import com.birdie.birdie.booking.expenses.ExpenseType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ExpenseCreationDTO(

        ExpenseType type,

        String title,

        String description,

        Double multiplier,

        Double value

) {
}
