package com.example.weather.client.services;

import com.example.weather.client.client.WeatherClient;
import com.example.weather.client.mappers.WeatherDataMapper;
import com.example.weather.client.repositories.WeatherDataRepo;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Setter
@Service
@RequiredArgsConstructor
public class WeatherDataService {

    private final WeatherDataRepo weatherDataRepo;
    private final WeatherClient weatherClient;
    private final WeatherDataMapper weatherDataMapper;

    @Scheduled(fixedRate = 1000)
    public void scheduledSaveWeatherData() {
        saveWeatherDataForCity("Warsaw");
        saveWeatherDataForCity("New York");
        saveWeatherDataForCity("London");
        saveWeatherDataForCity("Madrid");
        saveWeatherDataForCity("Berlin");
    }

    public void saveWeatherDataForCity(String city) {
        weatherDataRepo.save(weatherDataMapper.toEntity(weatherClient.getWeather(city)));
    }
}
