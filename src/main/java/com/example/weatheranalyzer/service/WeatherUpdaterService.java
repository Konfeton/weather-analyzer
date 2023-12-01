package com.example.weatheranalyzer.service;

import com.example.weatheranalyzer.dto.api.WeatherDto;
import com.example.weatheranalyzer.model.Location;
import com.example.weatheranalyzer.model.Weather;
import com.example.weatheranalyzer.service.utils.WeatherConverter;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
@Slf4j
public class WeatherUpdaterService {

    private final WeatherService weatherService;
    private final LocationService locationService;

    public WeatherUpdaterService(WeatherService weatherService, LocationService locationService) {
        this.weatherService = weatherService;
        this.locationService = locationService;
    }

    @Scheduled(cron = "${cron.everyFiveMinutes}")
    public void update() throws IOException, InterruptedException {
        log.info("Creating request to weather api");
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://weatherapi-com.p.rapidapi.com/current.json?q=Minsk"))
                .header("X-RapidAPI-Key", "fae28847aamsh05f729abb592cb8p1abca4jsn5d5674ed1234")
                .header("X-RapidAPI-Host", "weatherapi-com.p.rapidapi.com")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        log.info("Sending request");
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        log.debug("Response: {}", response.body());
        ObjectMapper objectMapper = new ObjectMapper();
        WeatherDto weatherDto = objectMapper.readValue(response.body(), WeatherDto.class);
        log.debug("Converting weatherDto to entity");
        Weather weather = WeatherConverter.fromWeatherDtoToEntity(weatherDto);
        Location location = WeatherConverter.fromLocationDtoToEntity(weatherDto.getLocation());
        weather.setLocation(location);
        log.debug("Saving {} ", weather);
        locationService.save(location);
        weatherService.save(weather);
        log.info("Weather saved");
    }
}
