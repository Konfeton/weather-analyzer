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

}
