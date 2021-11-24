package com.example.weather.client.controller;

import com.example.weather.client.models.dto.WeatherDataDto;
import com.example.weather.client.services.WeatherDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/weather/data")
public class WeatherDataController {

    private final WeatherDataService weatherDataService;

    @GetMapping
    public List<WeatherDataDto> getWeatherDataByCity(
            @RequestParam("city") String city,
            @RequestParam("page") int page,
            @RequestParam("size") int size) {
        return weatherDataService.getWeatherDataByCity(city, page, size);
    }
}
