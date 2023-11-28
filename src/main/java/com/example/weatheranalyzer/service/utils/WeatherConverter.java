package com.example.weatheranalyzer.service.utils;

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
        location.setLat(locationDto.getLat());
        location.setLon(locationDto.getLon());
        location.setTzId(locationDto.getTzId());
        location.setLocalTimeEpoch(locationDto.getLocaltimeEpoch());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        location.setLocalTime(LocalDateTime.parse(locationDto.getLocaltime(), formatter));
        return location;
    }

    
}
