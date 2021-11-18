package com.example.weather.client.models.dto;

import com.example.weather.client.models.entity.Coord;
import com.example.weather.client.models.entity.Main;
import com.example.weather.client.models.entity.Weather;
import com.example.weather.client.models.entity.Wind;
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
    private Coord coord;
    private List<Weather> weather;
    private Main main;
    private Wind wind;
    private Long dt;
    private String name;
}
