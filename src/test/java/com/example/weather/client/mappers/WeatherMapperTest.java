package com.example.weather.client.mappers;

import com.example.weather.client.models.dto.WeatherDto;
import com.example.weather.client.models.entity.Weather;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.example.weather.client.utility.PodamUtility.makePojo;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class WeatherMapperTest {

    private static final WeatherDto WEATHER_DTO = makePojo(WeatherDto.class);
    private static final List<WeatherDto> WEATHER_DTO_LIST = List.of(WEATHER_DTO);
    private final WeatherMapper weatherMapper = new WeatherMapper();

    @Test
    void testToEntity() {
        var weather = weatherMapper.toEntity(WEATHER_DTO);
        assertNotEquals(null, weather);
        assertEquals(WEATHER_DTO.getMain(), weather.getParameters());
        assertEquals(WEATHER_DTO.getDescription(), weather.getDescription());
    }

    @Test
    void testToEntityList() {
        var weatherList = weatherMapper.toEntityList(WEATHER_DTO_LIST);
        var weatherParametersList = weatherList.stream().map(Weather::getParameters).toList();
        var weatherDtoParametersList = WEATHER_DTO_LIST.stream().map(WeatherDto::getMain).toList();
        var weatherDescriptionList = weatherList.stream().map(Weather::getDescription).toList();
        var weatherDtoDescriptionList = WEATHER_DTO_LIST.stream().map(WeatherDto::getDescription).toList();
        assertThat(weatherList).isNotNull();
        assertEquals(weatherParametersList, weatherDtoParametersList);
        assertEquals(weatherDescriptionList, weatherDtoDescriptionList);
    }
}