package com.example.weatheranalyzer.repository;

import com.example.weatheranalyzer.model.Weather;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WeatherRepository extends JpaRepository<Weather, Long> {
}
