package com.example.weather.client.mappers;

import com.example.weather.client.models.dto.WeatherDto;
import com.example.weather.client.models.entity.Weather;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.example.weather.client.utility.PodamUtility.makePojo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class WeatherMapperTest {

    private WeatherDto weatherDto = makePojo(WeatherDto.class);
    private List<WeatherDto> weatherDtoList = List.of(weatherDto);
    private final WeatherMapper weatherMapper = new WeatherMapper();

    @Test
    void testToEntity() {
        var weather = weatherMapper.toEntity(weatherDto);
        assertNotNull(weather);
        assertEquals(weatherDto.getMain(), weather.getParameters());
        assertEquals(weatherDto.getDescription(), weather.getDescription());
    }

    @Test
    void testToEntityList() {
        var weatherList = weatherMapper.toEntityList(weatherDtoList);
        var weatherParametersList = weatherList.stream().map(Weather::getParameters).toList();
        var weatherDtoParametersList = weatherDtoList.stream().map(WeatherDto::getMain).toList();
        var weatherDescriptionList = weatherList.stream().map(Weather::getDescription).toList();
        var weatherDtoDescriptionList = weatherDtoList.stream().map(WeatherDto::getDescription).toList();
        assertNotNull(weatherList);
        assertEquals(weatherParametersList, weatherDtoParametersList);
        assertEquals(weatherDescriptionList, weatherDtoDescriptionList);
    }
}