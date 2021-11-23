package com.example.weather.client.services;

import com.example.weather.client.client.WeatherClient;
import com.example.weather.client.mappers.WeatherDataMapper;
import com.example.weather.client.repositories.WeatherDataRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class WeatherDataService {

    private final WeatherDataRepo weatherDataRepo;
    private final WeatherClient weatherClient;
    private final WeatherDataMapper weatherDataMapper;

    @Async
    @Scheduled(fixedDelay = 30, timeUnit = TimeUnit.MINUTES)
    public void saveWeatherDataForWarsaw() {
        saveWeatherDataForCity("Warsaw");
    }

    @Async
    @Scheduled(fixedDelay = 30, timeUnit = TimeUnit.MINUTES)
    public void saveWeatherDataForNewYork() {
        saveWeatherDataForCity("New York");
    }

    @Async
    @Scheduled(fixedDelay = 30, timeUnit = TimeUnit.MINUTES)
    public void saveWeatherDataForLondon() {
        saveWeatherDataForCity("London");
    }

    @Async
    @Scheduled(fixedDelay = 30, timeUnit = TimeUnit.MINUTES)
    public void saveWeatherDataForMadrid() {
        saveWeatherDataForCity("Madrid");
    }

    @Async
    @Scheduled(fixedDelay = 30, timeUnit = TimeUnit.MINUTES)
    public void saveWeatherDataForBerlin() {
        saveWeatherDataForCity("Berlin");
    }

    private void saveWeatherDataForCity(String city) {
        weatherDataRepo.save(weatherDataMapper.toEntity(weatherClient.getWeather(city)));
    }
}
