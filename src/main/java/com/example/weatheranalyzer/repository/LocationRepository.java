package com.example.weatheranalyzer.repository;

import com.example.weatheranalyzer.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, Long> {
}
