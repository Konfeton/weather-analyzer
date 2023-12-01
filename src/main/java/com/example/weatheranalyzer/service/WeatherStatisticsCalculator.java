package com.example.weatheranalyzer.service;

import com.example.weatheranalyzer.dto.AverageWeatherResponseDto;
import com.example.weatheranalyzer.model.Weather;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.function.ToDoubleFunction;
import java.util.stream.Collectors;

@Component
public class WeatherStatisticsCalculator {

    private static double calculateAverage(List<Weather> weatherList, ToDoubleFunction<Weather> extractor) {
        return weatherList.stream()
                .mapToDouble(extractor)
                .average()
                .orElse(0.0);
    }

    private static Map<LocalDate, Weather> calculateAverageByDays(Map<LocalDate, List<Weather>> weatherMap) {
        return weatherMap.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> new Weather(
                                calculateAverage(entry.getValue(), Weather::getTemperature),
                                calculateAverage(entry.getValue(), Weather::getWind),
                                calculateAverage(entry.getValue(), Weather::getPressure),
                                (int) calculateAverage(entry.getValue(), Weather::getHumidity)
                        )
                ));
    }

    public static AverageWeatherResponseDto calculate(Map<LocalDate, List<Weather>> weatherMap) {
        Map<LocalDate, Weather> averageByDays = calculateAverageByDays(weatherMap);
        List<Weather> weatherList = averageByDays.values().stream().collect(Collectors.toList());
        return new AverageWeatherResponseDto(
                Math.round(calculateAverage(weatherList, Weather::getTemperature)),
                Math.round(calculateAverage(weatherList, Weather::getWind)),
                Math.round(calculateAverage(weatherList, Weather::getPressure)),
                Math.round(calculateAverage(weatherList, Weather::getHumidity))
        );

    }

}
