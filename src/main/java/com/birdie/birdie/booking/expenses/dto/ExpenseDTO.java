package com.birdie.birdie.booking.expenses.dto;

import com.birdie.birdie.booking.expenses.ExpenseType;
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
