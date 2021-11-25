package com.example.weather.client.controllers;

import com.example.weather.client.models.dto.WeatherDataDto;
import com.example.weather.client.services.WeatherDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

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

    @DeleteMapping
    public void deleteWeatherDataInTimePeriod(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fromTime,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime toTime) {
        weatherDataService.deleteWeatherDataInTimePeriod(fromTime, toTime);
    }
}
