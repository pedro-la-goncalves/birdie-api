package com.birdie.api.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler({ EntityNotFoundException.class, NoSuchElementException.class })
    public ResponseEntity handleNotFoundExceptions() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handleBadRequestExceptions(MethodArgumentNotValidException exception) {
        List<FieldError> fieldErrorList = exception.getFieldErrors();
        List<InvalidFieldErrorDTO> errors = fieldErrorList.stream().map(InvalidFieldErrorDTO::new).toList();

        ApiErrorDTO apiErrorDTO = new ApiErrorDTO(400, "BAD_REQUEST");
        apiErrorDTO.setErrors(errors);

        return ResponseEntity.badRequest().body(apiErrorDTO);
    }
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity handleForbiddenExceptions() {
        List<InvalidFieldErrorDTO> errors = new ArrayList<>(List.of(new InvalidFieldErrorDTO("NOT_AVAILABLE", "contact.value", "contact is not available")));

        ApiErrorDTO apiErrorDTO = new ApiErrorDTO(403, "FORBIDDEN");
        apiErrorDTO.setErrors(errors);

        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(apiErrorDTO);
    }
}
