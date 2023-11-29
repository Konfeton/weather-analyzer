package com.example.weatheranalyzer.service.utils;

import com.example.weatheranalyzer.dto.LocationResponseDto;
import com.example.weatheranalyzer.dto.WeatherResponseDto;
import com.example.weatheranalyzer.dto.api.LocationDto;
import com.example.weatheranalyzer.dto.api.WeatherDto;
import com.example.weatheranalyzer.model.Location;
import com.example.weatheranalyzer.model.Weather;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class WeatherConverter {
    public static Weather fromWeatherDtoToEntity(WeatherDto weatherDto) {
        Weather weather = new Weather();
        weather.setTemperature(weatherDto.getCurrent().getTempC());
        weather.setWind(weatherDto.getCurrent().getWindMph());
        weather.setPressure(weatherDto.getCurrent().getPressureMb());
        weather.setHumidity(weatherDto.getCurrent().getHumidity());
        weather.setCondition(weatherDto.getCurrent().getCondition().getText());
        return weather;
    }

    public static Location fromLocationDtoToEntity(LocationDto locationDto) {
        Location location = new Location();
        location.setName(locationDto.getName());
        location.setRegion(locationDto.getRegion());
        location.setCountry(locationDto.getCountry());
        location.setLatitude(locationDto.getLat());
        location.setLongitude(locationDto.getLon());
        location.setTimeZoneName(locationDto.getTzId());
        location.setLocalTimeEpoch(locationDto.getLocaltimeEpoch());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        location.setLocalDateTime(LocalDateTime.parse(locationDto.getLocaltime(), formatter));
        return location;
    }

    public static WeatherResponseDto fromEntityToWeatherResponseDto(Weather weather) {
        WeatherResponseDto weatherResponseDto = new WeatherResponseDto();
        weatherResponseDto.setTemperature(weather.getTemperature());
        weatherResponseDto.setWind(weather.getWind());
        weatherResponseDto.setPressure(weather.getPressure());
        weatherResponseDto.setHumidity(weather.getHumidity());
        weatherResponseDto.setCondition(weather.getCondition());
        return weatherResponseDto;
    }

    public static LocationResponseDto fromEntityToLocationResponseDto(Location location) {
        LocationResponseDto locationResponseDto = new LocationResponseDto();
        locationResponseDto.setName(location.getName());
        locationResponseDto.setRegion(location.getRegion());
        locationResponseDto.setCountry(location.getCountry());
        locationResponseDto.setLat(location.getLatitude());
        locationResponseDto.setLon(location.getLongitude());
        locationResponseDto.setTzId(location.getTimeZoneName());
        locationResponseDto.setLocalTimeEpoch(location.getLocalTimeEpoch());
        locationResponseDto.setLocalTime(location.getLocalDateTime());
        return locationResponseDto;
    }
}
