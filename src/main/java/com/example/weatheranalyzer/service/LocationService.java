package com.example.weatheranalyzer.service;

import com.example.weatheranalyzer.exception.NoElementsException;
import com.example.weatheranalyzer.model.Location;
import com.example.weatheranalyzer.repository.LocationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
@Slf4j
public class LocationService {
    private final LocationRepository locationRepository;

    public LocationService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    public void save(Location location) {
        locationRepository.save(location);
    }

    public List<Location> findWeatherBetweenDates(LocalDate from, LocalDate to) throws NoElementsException {
        log.debug("Fetching locations between {} and {}", from, to);
        List<Location> locations = locationRepository.findByLocalDateTimeBetween(from.atStartOfDay(), to.atTime(LocalTime.MAX));
        if (locations.isEmpty()) {
            log.debug("No locations found");
            throw new NoElementsException("No records in range: " + from + " to " + to);
        }

        return locations;
    }
}
