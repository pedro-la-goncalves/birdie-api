package com.birdie.birdie.mvc.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EDefaultHours {
    CHECK_IN("14:00:00"),
    CHECK_OUT("12:00:00");

    private final String value;
}
