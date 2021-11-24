package com.example.weather.client.controllers;

import com.example.weather.client.services.WeatherDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/weather/data")
public class WeatherDataController {

    private final WeatherDataService weatherDataService;

    @DeleteMapping
    public void deleteWeatherDataInTimePeriod(
            @RequestParam("fromTime") Long fromTime,
            @RequestParam("toTime") Long toTime) {
        weatherDataService.deleteWeatherDataInTimePeriod(fromTime, toTime);
    }
}
