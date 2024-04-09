package com.birdie.api.booking.reservation.expense.dto;

import com.birdie.api.booking.reservation.expense.ExpenseType;
import jakarta.validation.constraints.NotNull;

public record ExpenseDTO(

        @NotNull
        Long id,

        ExpenseType type,

        String title,

        String description,

        Double multiplier,

        Double value

) {
}
