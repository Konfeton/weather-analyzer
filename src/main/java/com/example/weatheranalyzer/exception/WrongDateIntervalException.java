package com.example.weatheranalyzer.exception;

public class WrongDateIntervalException extends RuntimeException {
    public WrongDateIntervalException(String message) {
        super(message);
    }
}
