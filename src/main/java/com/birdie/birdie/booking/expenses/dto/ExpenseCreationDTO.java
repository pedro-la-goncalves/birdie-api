package com.birdie.birdie.booking.expenses.dto;

import com.birdie.birdie.booking.expenses.EExpenseType;

public record ExpenseCreationDTO(

        EExpenseType type,

        String title,

        String description,

        Double multiplier,

        Double value

) {
}
