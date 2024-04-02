package com.birdie.birdie.booking.expenses.dto;

import com.birdie.birdie.booking.expenses.EExpenseType;
import jakarta.validation.constraints.NotNull;

public record ExpenseUpdateDTO(

        @NotNull
        Long id,

        EExpenseType type,

        String title,

        String description,

        Double multiplier,

        Double value

) {
}
