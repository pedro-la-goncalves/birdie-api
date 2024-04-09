package com.birdie.api.booking.reservation.expense.dto;

import com.birdie.api.booking.reservation.expense.ExpenseType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotNull;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ExpenseUpdateDTO(

        @NotNull
        Long id,

        ExpenseType type,

        String title,

        String description,

        Double multiplier,

        Double value

) {
}
