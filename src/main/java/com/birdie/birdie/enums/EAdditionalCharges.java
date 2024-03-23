package com.birdie.birdie.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EAdditionalCharges {
    PARKING_WEEKDAY(15.0),
    PARKING_WEEKEND(20.0);

    private final double value;
}
