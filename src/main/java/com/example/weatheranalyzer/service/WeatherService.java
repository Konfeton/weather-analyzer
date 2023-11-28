package com.example.weatheranalyzer.service;

import com.example.weatheranalyzer.model.Weather;
import com.example.weatheranalyzer.repository.WeatherRepository;
import org.springframework.stereotype.Service;

@Service
public class WeatherService {
    private final WeatherRepository weatherRepository;
    private final LocationService locationService;

    public WeatherService(WeatherRepository weatherRepository, LocationService locationService) {
        this.weatherRepository = weatherRepository;
        this.locationService = locationService;
    }

    public void save(Weather weather) {
        weatherRepository.save(weather);
    }


}
