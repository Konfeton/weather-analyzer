package com.example.weatheranalyzer.repository;

import com.example.weatheranalyzer.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {
    List<Location> findByLocalTimeBetween(LocalDateTime from, LocalDateTime to);
}
