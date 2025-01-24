package br.com.app.app_control.infrastructure.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.app.app_control.infrastructure.record.ResponseRecord;

@RestControllerAdvice
public class ValidationExceptionHandler {
    private static final String GENERIC_ERROR_MESSAGE = "Erro ao validar informações.";

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseRecord> handleValidationExceptions(MethodArgumentNotValidException ex) {
        List<String> errors = new ArrayList<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.add(error.getDefaultMessage());
        }
        ResponseRecord response = new ResponseRecord(GENERIC_ERROR_MESSAGE, errors); 
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
