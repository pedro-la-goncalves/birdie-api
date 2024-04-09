package com.birdie.api.exception;

import org.springframework.validation.FieldError;

public record InvalidFieldErrorDTO(String type, String field, String message) implements ErrorDTO {
    public InvalidFieldErrorDTO(FieldError fieldError) {
        this("INVALID_FIELD", fieldError.getField(), fieldError.getDefaultMessage());
    }
}
