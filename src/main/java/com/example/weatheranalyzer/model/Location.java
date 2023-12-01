package com.example.weatheranalyzer.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "Location")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "region")
    private String region;
    @Column(name = "country")
    private String country;
    @Column(name = "latitude")
    private Double latitude;
    @Column(name = "longitude")
    private Double longitude;
    @Column(name = "time_zone_name")
    private String timeZoneName;
    @Column(name = "local_time_epoch")
    private Integer localTimeEpoch;
    @Column(name = "local_date_time")
    private LocalDateTime localDateTime;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Location location)) return false;

        if (!getId().equals(location.getId())) return false;
        if (!getName().equals(location.getName())) return false;
        if (!getRegion().equals(location.getRegion())) return false;
        if (!getCountry().equals(location.getCountry())) return false;
        if (!getLatitude().equals(location.getLatitude())) return false;
        if (!getLongitude().equals(location.getLongitude())) return false;
        if (!getTimeZoneName().equals(location.getTimeZoneName())) return false;
        if (!getLocalTimeEpoch().equals(location.getLocalTimeEpoch())) return false;
        return getLocalDateTime().equals(location.getLocalDateTime());
    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + getName().hashCode();
        result = 31 * result + getRegion().hashCode();
        result = 31 * result + getCountry().hashCode();
        result = 31 * result + getLatitude().hashCode();
        result = 31 * result + getLongitude().hashCode();
        result = 31 * result + getTimeZoneName().hashCode();
        result = 31 * result + getLocalTimeEpoch().hashCode();
        result = 31 * result + getLocalDateTime().hashCode();
        return result;
    }
}