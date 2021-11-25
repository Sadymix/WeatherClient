package com.example.weather.client.controller;

import com.example.weather.client.models.dto.WeatherDataDto;
import com.example.weather.client.services.WeatherDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/weather/data")
public class WeatherDataController {

    private final WeatherDataService weatherDataService;

    @GetMapping
    public Page<WeatherDataDto> getWeatherDataByCity(
            @RequestParam String city,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return weatherDataService.getWeatherDataByCity(city, page, size);
    }
}
