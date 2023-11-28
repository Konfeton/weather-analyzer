package com.example.weatheranalyzer.controller;

import com.example.weatheranalyzer.dto.WeatherResponseDto;
import com.example.weatheranalyzer.service.WeatherService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
