package com.example.weatheranalyzer.controller;

import com.example.weatheranalyzer.dto.AverageWeatherResponseDto;
import com.example.weatheranalyzer.dto.WeatherResponseDto;
import com.example.weatheranalyzer.exception.DateOutOfBoundsException;
import com.example.weatheranalyzer.exception.InvalidDateIntervalException;
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

    @GetMapping("/avg-weather")
    @ResponseStatus(HttpStatus.OK)
    public AverageWeatherResponseDto getAvgTemperature(@RequestParam(required = false) LocalDate from,
                                                       @RequestParam(required = false) LocalDate to) {
        if (from == null && to == null) {
            return weatherService.getAverageWeatherBetweenDates(LocalDate.now(), LocalDate.now());
        } else if (to == null) {
            return weatherService.getAverageWeatherBetweenDates(from, LocalDate.now());
        } else if (from == null) {
            throw new InvalidDateIntervalException("Param 'from' is missing");
        }

        if (to.isAfter(LocalDate.now())) {
            throw new DateOutOfBoundsException("Date should be equals or less than " + LocalDate.now());
        }
        if (from.isAfter(to)) {
            throw new InvalidDateIntervalException("Invalid date interval from: " + from + " to " + to);
        }
        return weatherService.getAverageWeatherBetweenDates(from, to);
    }
}
