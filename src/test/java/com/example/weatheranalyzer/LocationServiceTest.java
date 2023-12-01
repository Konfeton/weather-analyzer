package com.example.weatheranalyzer;

import com.example.weatheranalyzer.exception.NoElementsException;
import com.example.weatheranalyzer.repository.LocationRepository;
import com.example.weatheranalyzer.service.LocationService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collections;

import static org.mockito.Mockito.when;

@SpringBootTest
public class LocationServiceTest {
    @Mock
    private LocationRepository locationRepository;
    @InjectMocks
    private LocationService locationService;

    @Test
    public void testFindWeatherBetweenDates() {
        LocalDateTime from = LocalDate.of(2023, 11, 20).atStartOfDay();
        LocalDateTime to = from.plusDays(1).toLocalDate().atTime(LocalTime.MAX);
        when(locationRepository.findByLocalDateTimeBetween(from, to)).thenReturn(Collections.emptyList());

        Assertions.assertThrows(NoElementsException.class, () -> locationService.findWeatherBetweenDates(from.toLocalDate(), to.toLocalDate()));

    }
}
