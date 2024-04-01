package com.birdie.birdie.mvc.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EAdditionalCharges {
    PARKING_WORKDAY(15.0, null),
    PARKING_WEEKEND(20.0, null),
    LATE_CHECKOUT(null, 0.5);

    private final Double value;
    private final Double multiplier;
}
