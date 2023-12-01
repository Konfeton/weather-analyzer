package com.example.weatheranalyzer.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Weather")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Weather {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "temperature")
    private Double temperature;
    @Column(name = "wind")
    private Double wind;
    @Column(name = "pressure")
    private Double pressure;
    @Column(name = "humidity")
    private Integer humidity;
    @Column(name = "weather_condition")
    private String condition;
    @OneToOne
    @JoinColumn(name = "location_id", referencedColumnName = "id")
    private Location location;

    public Weather(Double temperature, Double wind, Double pressure, Integer humidity) {
        this.temperature = temperature;
        this.wind = wind;
        this.pressure = pressure;
        this.humidity = humidity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Weather weather)) return false;

        if (!getId().equals(weather.getId())) return false;
        if (!getTemperature().equals(weather.getTemperature())) return false;
        if (!getWind().equals(weather.getWind())) return false;
        if (!getPressure().equals(weather.getPressure())) return false;
        if (!getHumidity().equals(weather.getHumidity())) return false;
        return getCondition().equals(weather.getCondition());
    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + getTemperature().hashCode();
        result = 31 * result + getWind().hashCode();
        result = 31 * result + getPressure().hashCode();
        result = 31 * result + getHumidity().hashCode();
        result = 31 * result + getCondition().hashCode();
        return result;
    }
}
