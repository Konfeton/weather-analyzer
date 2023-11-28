package com.example.weatheranalyzer.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class WeatherResponseDto {
    private Double temperature;
    private Double wind;
    private Double pressure;
    private Integer humidity;
    private String condition;
    private LocationResponseDto location;
}
