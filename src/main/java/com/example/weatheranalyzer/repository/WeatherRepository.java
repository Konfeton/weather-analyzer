package com.example.weatheranalyzer.repository;

import com.example.weatheranalyzer.model.Location;
import com.example.weatheranalyzer.model.Weather;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WeatherRepository extends JpaRepository<Weather, Long> {
    Weather findFirstByOrderByIdDesc();
    List<Weather> findByLocationIn(List<Location> location);
}
