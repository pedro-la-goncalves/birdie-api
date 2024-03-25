package com.birdie.birdie.handler;

import com.birdie.birdie.dto.InvalidFieldDTO;
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
        List<InvalidFieldDTO> invalidFieldDTOList = fieldErrorList.stream().map(InvalidFieldDTO::new).toList();

        return ResponseEntity.badRequest().body(invalidFieldDTOList);
    }
}
