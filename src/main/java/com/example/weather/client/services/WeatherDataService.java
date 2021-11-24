package com.example.weather.client.services;

import com.example.weather.client.client.WeatherClient;
import com.example.weather.client.mappers.WeatherDataMapper;
import com.example.weather.client.models.dto.WeatherDataDto;
import com.example.weather.client.repositories.WeatherDataRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

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

    public List<WeatherDataDto> getWeatherDataByCity(String city, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("unixTime").ascending());
        var weatherData = weatherDataRepo.findAllByCityName(city, pageable);
        return weatherData.getContent().stream()
                .map(weatherDataMapper::toDto)
                .collect(Collectors.toList());
    }

    private void saveWeatherDataForCity(String city) {
        weatherDataRepo.save(weatherDataMapper.toEntity(weatherClient.getWeather(city)));
    }
}
