package com.example.weather.client.client;

import com.example.weather.client.models.dto.WeatherDataDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Component
@RequiredArgsConstructor
public class WeatherClient {

    private final RestTemplate restTemplate;
    @Value("${app.url.base}")
    private String url;

    public WeatherDataDto getWeather(String city) {
        return restTemplate.getForObject(url + "?q=" + city,
                WeatherDataDto.class);
    }
}
