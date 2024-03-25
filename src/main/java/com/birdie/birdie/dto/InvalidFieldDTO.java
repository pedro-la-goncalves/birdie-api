package com.birdie.birdie.dto;

import org.springframework.validation.FieldError;

public record InvalidFieldDTO(String field, String message) {
    public InvalidFieldDTO(FieldError fieldError) {
        this(fieldError.getField(), fieldError.getDefaultMessage());
    }
}
