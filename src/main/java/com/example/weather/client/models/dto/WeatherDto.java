package com.example.weather.client.models.dto;

import com.example.weather.client.models.entity.GeoCoordinates;
import com.example.weather.client.models.entity.Main;
import com.example.weather.client.models.entity.Weather;
import com.example.weather.client.models.entity.Wind;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WeatherDto {
    private GeoCoordinates coord;
    private List<Weather> weather;
    private Main main;
    private Wind wind;
    @JsonProperty("dt")
    private Long unixTime;
    private String name;
}
