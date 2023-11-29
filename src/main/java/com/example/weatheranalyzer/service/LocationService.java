package com.example.weatheranalyzer.service;

import com.example.weatheranalyzer.model.Location;
import com.example.weatheranalyzer.repository.LocationRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
public class LocationService {
    private final LocationRepository locationRepository;

    public LocationService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    public void save(Location location) {
        locationRepository.save(location);
    }

    public List<Location> findWeatherBetweenDates(LocalDate from, LocalDate to) {
        List<Location> locations = locationRepository.findByLocalDateTimeBetween(from.atStartOfDay(), to.atTime(LocalTime.MAX));
        return locations;
    }
}
