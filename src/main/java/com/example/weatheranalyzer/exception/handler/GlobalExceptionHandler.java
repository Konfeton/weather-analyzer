package com.example.weatheranalyzer.exception.handler;

import com.example.weatheranalyzer.exception.DateOutOfBoundsException;
import com.example.weatheranalyzer.exception.InvalidDateIntervalException;
import com.example.weatheranalyzer.exception.NoElementsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(DateOutOfBoundsException.class)
    public ResponseEntity<Object> handleDateOutOfBoundException(DateOutOfBoundsException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<String> handleTypeMismatchException(MethodArgumentTypeMismatchException ex) {
        return new ResponseEntity<>("Invalid date format: " + ex.getValue(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidDateIntervalException.class)
    public ResponseEntity<String> handleWrongDateIntervalException(InvalidDateIntervalException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NoElementsException.class)
    public ResponseEntity<String> handleNoElementsException(NoElementsException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.SERVICE_UNAVAILABLE);
    }
}
