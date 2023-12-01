package com.example.weatheranalyzer;

import com.example.weatheranalyzer.dto.AverageWeatherResponseDto;
import com.example.weatheranalyzer.dto.LocationResponseDto;
import com.example.weatheranalyzer.dto.WeatherResponseDto;
import com.example.weatheranalyzer.model.Location;
import com.example.weatheranalyzer.model.Weather;
import com.example.weatheranalyzer.repository.WeatherRepository;
import com.example.weatheranalyzer.service.LocationService;
import com.example.weatheranalyzer.service.WeatherService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


@SpringBootTest
public class WeatherServiceTest {
    @Mock
    private WeatherRepository weatherRepository;
    @Mock
    private LocationService locationService;
    @InjectMocks
    private WeatherService weatherService;

    private Weather mockWeather;
    private Location mockLocation;
    private WeatherResponseDto mockWeatherResponseDto;
    private LocationResponseDto mockLocationResponseDto;
    private AverageWeatherResponseDto mockAverageWeatherResponseDto;


    @BeforeEach
    void setUp() {
        long id = 1L;
        String city = "Minsk";
        String region = "Minsk";
        String country = "Belarus";
        double latitude = 53.9;
        double longitude = 27.57;
        String timeZoneName = "Europe/Minsk";
        int localTimeEpoch = 1701180684;
        LocalDateTime localDateTime = LocalDateTime.parse("2023-11-28 17:11:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        double temperature = -6.0;
        double wind = 9.0;
        double pressure = 1001.0;
        int humidity = 86;
        String condition = "Partly cloudy";
        mockLocation = new Location(id, city, region, country, latitude, longitude, timeZoneName, localTimeEpoch, localDateTime);
        mockWeather = new Weather(id, temperature, wind, pressure, humidity, condition, mockLocation);
        mockLocationResponseDto = new LocationResponseDto(city, region, country, latitude, longitude, timeZoneName, localTimeEpoch, localDateTime);
        mockWeatherResponseDto = new WeatherResponseDto(temperature, wind, pressure, humidity, condition, mockLocationResponseDto);
        mockAverageWeatherResponseDto = new AverageWeatherResponseDto(temperature, wind, pressure, humidity);

    }

    @Test
    public void testSave() {
        when(weatherService.save(mockWeather)).thenReturn(mockWeather);

        Weather saved = weatherService.save(mockWeather);

        assertEquals(mockWeather, saved);
        verify(weatherRepository, times(1)).save(any());
    }


    @Test
    void testGetCurrentWeather() {
        when(weatherRepository.findFirstByOrderByIdDesc()).thenReturn(mockWeather);

        WeatherResponseDto currentWeather = weatherService.getCurrentWeather();

        assertEquals(mockWeatherResponseDto, currentWeather);
    }

    @Test
    void testGetAverageTemperatureBetweenDates() {
        LocalDate localDate = mockWeather.getLocation().getLocalDateTime().toLocalDate();
        when(locationService.findWeatherBetweenDates(localDate, localDate.plusDays(1))).thenReturn(List.of(mockLocation));
        when(weatherRepository.findByLocationIn(List.of(mockLocation))).thenReturn(List.of(mockWeather));

        AverageWeatherResponseDto averageWeatherBetweenDates = weatherService.getAverageWeatherBetweenDates(localDate, localDate.plusDays(1));
        assertEquals(mockAverageWeatherResponseDto, averageWeatherBetweenDates);

    }
}
