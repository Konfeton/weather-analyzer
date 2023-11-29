package com.example.weatheranalyzer.controller;

import com.example.weatheranalyzer.dto.WeatherResponseDto;
import com.example.weatheranalyzer.exception.DateOutOfBoundsException;
import com.example.weatheranalyzer.exception.WrongDateIntervalException;
import com.example.weatheranalyzer.service.WeatherService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
public class WeatherController {
    private final WeatherService weatherService;

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/weather")
    public WeatherResponseDto getCurrentWeather() {
        return weatherService.getCurrentWeather();
    }

    @GetMapping("/avg-temp")
    @ResponseStatus(HttpStatus.OK)
    public double getAvgTemperature(@RequestParam LocalDate from,
                                    @RequestParam LocalDate to) {
        if (to.isAfter(LocalDate.now())) {
            throw new DateOutOfBoundsException("Date should be equals or less than " + LocalDate.now());
        }
        if (from.isAfter(to)) {
            throw new WrongDateIntervalException("Invalid date interval from: " + from + " to " + to);
        }
        return weatherService.getAverageTemperatureBetweenDates(from, to);
    }
}
