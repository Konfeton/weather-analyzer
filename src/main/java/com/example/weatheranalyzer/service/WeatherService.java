package com.example.weatheranalyzer.service;

import com.example.weatheranalyzer.dto.LocationResponseDto;
import com.example.weatheranalyzer.dto.WeatherResponseDto;
import com.example.weatheranalyzer.model.Weather;
import com.example.weatheranalyzer.repository.WeatherRepository;
import com.example.weatheranalyzer.service.utils.WeatherConverter;
import org.springframework.stereotype.Service;

@Service
public class WeatherService {
    private final WeatherRepository weatherRepository;

    public WeatherService(WeatherRepository weatherRepository) {
        this.weatherRepository = weatherRepository;
    }

    public void save(Weather weather) {
        weatherRepository.save(weather);
    }

    public WeatherResponseDto getCurrentWeather() {
        Weather weatherFromDb = weatherRepository.findFirstByOrderByIdDesc();
        WeatherResponseDto weatherResponseDto = WeatherConverter.fromEntityToWeatherResponseDto(weatherFromDb);
        LocationResponseDto location = WeatherConverter.fromEntityToLocationResponseDto(weatherFromDb.getLocation());
        weatherResponseDto.setLocation(location);
        return weatherResponseDto;

    }


}
