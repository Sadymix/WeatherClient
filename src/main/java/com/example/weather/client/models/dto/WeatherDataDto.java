package com.example.weather.client.models.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class WeatherDataDto {
    private GeoCoordinatesDto coord;
    private List<WeatherDto> weather;
    private ConditionsDto main;
    private WindDto wind;
    @JsonProperty("dt")
    private Long unixTime;
    private String name;
}
