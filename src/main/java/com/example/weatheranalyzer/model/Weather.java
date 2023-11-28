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
    private Double temperature;
    private Double wind;
    private Double pressure;
    private Integer humidity;
    @Column(name = "weather_condition")
    private String condition;
    @OneToOne
    @JoinColumn(name = "location_id", referencedColumnName = "id")
    private Location location;

}
