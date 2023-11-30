package com.example.weatheranalyzer.service;

import com.example.weatheranalyzer.dto.AverageWeatherResponseDto;
import com.example.weatheranalyzer.dto.LocationResponseDto;
import com.example.weatheranalyzer.dto.WeatherResponseDto;
import com.example.weatheranalyzer.model.Location;
import com.example.weatheranalyzer.model.Weather;
import com.example.weatheranalyzer.repository.WeatherRepository;
import com.example.weatheranalyzer.service.utils.WeatherConverter;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class WeatherService {
    private final WeatherRepository weatherRepository;
    private final LocationService locationService;
    private final WeatherStatisticsCalculator weatherStatisticsCalculator;

    public WeatherService(WeatherRepository weatherRepository, LocationService locationService, WeatherStatisticsCalculator weatherStatisticsCalculator) {
        this.weatherRepository = weatherRepository;
        this.locationService = locationService;
        this.weatherStatisticsCalculator = weatherStatisticsCalculator;
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

    public AverageWeatherResponseDto getAverageWeatherBetweenDates(LocalDate from, LocalDate to) {
        List<Location> locations = locationService.findWeatherBetweenDates(from, to);
        List<Weather> listOfWeather = weatherRepository.findByLocationIn(locations);

        Map<LocalDate, List<Weather>> collectedByDate = listOfWeather.stream().collect(Collectors.groupingBy(item -> item.getLocation().getLocalDateTime().toLocalDate()));

        return weatherStatisticsCalculator.calculate(collectedByDate);
    }

}
