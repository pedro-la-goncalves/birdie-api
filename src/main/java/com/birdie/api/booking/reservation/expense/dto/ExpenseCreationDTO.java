package com.birdie.api.booking.reservation.expense.dto;

import com.birdie.api.booking.reservation.expense.ExpenseType;
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
