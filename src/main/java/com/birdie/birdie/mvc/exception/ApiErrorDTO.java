package com.birdie.birdie.mvc.exception;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ApiErrorDTO {
    private int code;
    private String description;
    private String dateTime = LocalDateTime.now().toString();
    private List<InvalidFieldErrorDTO> errors;

    public ApiErrorDTO(int code, String description) {
        this.code = code;
        this.description = description;
    }
}
