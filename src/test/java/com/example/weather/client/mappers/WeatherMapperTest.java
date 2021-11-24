package com.example.weather.client.mappers;

import com.example.weather.client.models.dto.WeatherDto;
import com.example.weather.client.models.entity.Weather;
import org.junit.jupiter.api.Test;

import static com.example.weather.client.utility.PodamUtility.makePojo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class WeatherMapperTest {

    private WeatherDto expectedWeatherDto = makePojo(WeatherDto.class);
    private Weather expectedWeather = makePojo(Weather.class);
    private final WeatherMapper weatherMapper = new WeatherMapper();

    @Test
    void testToEntity() {
        var weather = weatherMapper.toEntity(expectedWeatherDto);
        assertNotNull(weather);
        assertEquals(expectedWeatherDto.getMain(), weather.getParameters());
        assertEquals(expectedWeatherDto.getDescription(), weather.getDescription());
    }

    @Test
    void testToDto() {
        var weatherDto = weatherMapper.toDto(expectedWeather);
        assertNotNull(weatherDto);
        assertEquals(expectedWeather.getParameters(), weatherDto.getMain());
        assertEquals(expectedWeather.getDescription(), weatherDto.getDescription());
    }
}