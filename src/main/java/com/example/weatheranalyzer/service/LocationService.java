package com.example.weatheranalyzer.service;

import com.example.weatheranalyzer.model.Location;
import com.example.weatheranalyzer.repository.LocationRepository;
import org.springframework.stereotype.Service;

@Service
public class LocationService {
    private final LocationRepository locationRepository;

    public LocationService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    public void save(Location location) {
        locationRepository.save(location);
    }
}
