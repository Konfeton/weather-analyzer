package com.example.weatheranalyzer.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class LocationResponseDto {
    private String name;
    private String region;
    private String country;
    private Double lat;
    private Double lon;
    private String tzId;
    private Integer localTimeEpoch;
    private LocalDateTime localTime;
}
