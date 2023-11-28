package com.example.weatheranalyzer.dto.api;


import java.util.LinkedHashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;



@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "location",
        "current"
})
public class WeatherDto {
    @JsonProperty("location")
    private LocationDto location;
    @JsonProperty("current")
    private CurrentDto current;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("location")
    public LocationDto getLocation() {
        return location;
    }

    @JsonProperty("location")
    public void setLocation(LocationDto location) {
        this.location = location;
    }

    @JsonProperty("current")
    public CurrentDto getCurrent() {
        return current;
    }

    @JsonProperty("current")
    public void setCurrent(CurrentDto current) {
        this.current = current;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}

