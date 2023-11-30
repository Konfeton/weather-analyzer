package com.example.weatheranalyzer.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class AverageWeatherResponseDto {
    @JsonProperty("avg_tempC")
    private double avgTemperature;
    @JsonProperty("avg_wind_mph")
    private double avgWind;
    @JsonProperty("avg_pressure_mb")
    private double avgPressure;
    @JsonProperty("avg_humidity")
    private double avgHumidity;
}
