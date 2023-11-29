package com.example.weatheranalyzer.service;

import com.example.weatheranalyzer.dto.LocationResponseDto;
import com.example.weatheranalyzer.dto.WeatherResponseDto;
import com.example.weatheranalyzer.model.Location;
import com.example.weatheranalyzer.model.Weather;
import com.example.weatheranalyzer.repository.WeatherRepository;
import com.example.weatheranalyzer.service.utils.WeatherConverter;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    public WeatherResponseDto getCurrentWeather() {
        Weather weatherFromDb = weatherRepository.findFirstByOrderByIdDesc();
        WeatherResponseDto weatherResponseDto = WeatherConverter.fromEntityToWeatherResponseDto(weatherFromDb);
        LocationResponseDto location = WeatherConverter.fromEntityToLocationResponseDto(weatherFromDb.getLocation());
        weatherResponseDto.setLocation(location);
        return weatherResponseDto;
    }

    public double getAverageTemperatureBetweenDates(LocalDate from, LocalDate to) {
        List<Location> locations = locationService.findWeatherBetweenDates(from, to);
        List<Weather> listOfWeather = weatherRepository.findByLocationIn(locations);

        Map<LocalDate, List<Weather>> collectedByDate = listOfWeather.stream().collect(Collectors.groupingBy(item -> item.getLocation().getLocalDateTime().toLocalDate()));

        Map<LocalDate, Double> dailyAverageTemperature = getDailyAverageTemperature(collectedByDate);

        double totalAvgTemp = getTotalAvgTemp(dailyAverageTemperature);
        int numberOfDays = dailyAverageTemperature.size();
        double avgTemp = totalAvgTemp / numberOfDays;

        return new BigDecimal(avgTemp).setScale(1, RoundingMode.HALF_UP).doubleValue();
    }

    private double getTotalAvgTemp(Map<LocalDate, Double> dailyAverageTemperature) {
        double totalAvgTemp = 0;
        for (Double temp : dailyAverageTemperature.values()) {
            totalAvgTemp += temp;
        }
        return totalAvgTemp;
    }

    private Map<LocalDate, Double> getDailyAverageTemperature(Map<LocalDate, List<Weather>> collectedByDate) {
        Map<LocalDate, Double> avgTempPerDay = new HashMap<>();
        for (Map.Entry<LocalDate, List<Weather>> entry : collectedByDate.entrySet()) {
            double totalTemp = 0;
            for (Weather weather : entry.getValue()) {
                totalTemp += weather.getTemperature();
            }
            avgTempPerDay.put(entry.getKey(), totalTemp / entry.getValue().size());
        }
        return avgTempPerDay;
    }
}
