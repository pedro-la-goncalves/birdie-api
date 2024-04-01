package com.birdie.birdie.mvc.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EDailyRate {
    WORKDAY(120.0),
    WEEKEND(180.0);

    private final double value;
}
