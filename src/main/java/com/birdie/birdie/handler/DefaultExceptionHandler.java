package com.birdie.birdie.handler;

import com.birdie.birdie.exception.ApiErrorDTO;
import com.birdie.birdie.exception.InvalidFieldErrorDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class DefaultExceptionHandler {
    @ExceptionHandler({ EntityNotFoundException.class, NoSuchElementException.class })
    public ResponseEntity notFound() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity badRequest(MethodArgumentNotValidException exception) {
        List<FieldError> fieldErrorList = exception.getFieldErrors();
        List<InvalidFieldErrorDTO> errors = fieldErrorList.stream().map(InvalidFieldErrorDTO::new).toList();

        ApiErrorDTO apiErrorDTO = new ApiErrorDTO(400, "BAD_REQUEST");
        apiErrorDTO.setErrors(errors);

        return ResponseEntity.badRequest().body(apiErrorDTO);
    }
}
