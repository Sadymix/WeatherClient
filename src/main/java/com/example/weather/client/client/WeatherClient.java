package com.example.weather.client.client;

import com.example.weather.client.mappers.WeatherDataMapper;
import com.example.weather.client.models.dto.WeatherDataDto;
import com.example.weather.client.models.entity.WeatherData;
import com.example.weather.client.repositories.WeatherDataRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;

@Slf4j
@Component
@RequiredArgsConstructor
public class WeatherClient {

    private final RestTemplate restTemplate;
    private final WeatherDataRepo weatherDataRepo;
    private final WeatherDataMapper weatherDataMapper;
    @Value("${app.url.base}")
    private String url;
    @Value("${openweather.api.key}")
    private String keyValue;

    public WeatherData getWeather(String city) {
        var forObject = restTemplate.getForObject(url + "?q=" + city + "&appid=" + keyValue,
                WeatherDataDto.class);
        var save = weatherDataRepo.save(weatherDataMapper.toEntity(forObject));
        return save;
    }

    @PostConstruct
    public void postConstruct() {
        log.info(getWeather("Warsaw").toString());
    }
}
