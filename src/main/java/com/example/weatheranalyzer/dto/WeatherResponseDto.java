package com.example.weatheranalyzer.dto;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class WeatherResponseDto {
    private Double temperature;
    private Double wind;
    private Double pressure;
    private Integer humidity;
    private String condition;
    private LocationResponseDto location;
}
