package com.example.weather.client.services;

import com.example.weather.client.client.WeatherClient;
import com.example.weather.client.exceptions.ResourceToLargeException;
import com.example.weather.client.mappers.WeatherDataMapper;
import com.example.weather.client.models.dto.WeatherDataDto;
import com.example.weather.client.repositories.WeatherDataRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WeatherDataService {

    private static final int MAX_PAGE_SIZE = 100;
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


    public void deleteWeatherDataInTimePeriod(Long fromTime, Long toTime) {
        var weatherDataInTimePeriod =
                weatherDataRepo.findAllByUnixTimeGreaterThanAndUnixTimeLessThanEqual(fromTime, toTime);
        weatherDataRepo.deleteAll(weatherDataInTimePeriod);
    }

    public Page<WeatherDataDto> getWeatherDataByCity(String city, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("unixTime").ascending());
        if (size > MAX_PAGE_SIZE) {
            throw new ResourceToLargeException("Size of page out of bound");
        }
        var weatherData = weatherDataRepo.findAllByCityName(city, pageable);
        var weatherDataList = weatherData.stream()
                .map(weatherDataMapper::toDto)
                .collect(Collectors.toList());
        return new PageImpl<>(
                weatherDataList,
                pageable,
                weatherDataList.size());
    }

    public void deleteWeatherDataInTimePeriod(LocalDateTime fromTime, LocalDateTime toTime) {
        var weatherDataInTimePeriod =
                weatherDataRepo.findAllByUnixTimeGreaterThanAndUnixTimeLessThanEqual(
                        fromTime.toEpochSecond(ZoneOffset.UTC),
                        toTime.toEpochSecond(ZoneOffset.UTC));
        weatherDataRepo.deleteAll(weatherDataInTimePeriod);
    }
    private void saveWeatherDataForCity(String city) {
        weatherDataRepo.save(weatherDataMapper.toEntity(weatherClient.getWeather(city)));
    }
}
