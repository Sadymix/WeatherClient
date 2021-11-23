package com.example.weather.client.mappers;

import com.example.weather.client.models.dto.WeatherDto;
import com.example.weather.client.models.entity.Weather;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class WeatherMapperTest {

    private static PodamFactory FACTORY = new PodamFactoryImpl();
    private static final WeatherDto WEATHER_DTO = FACTORY.manufacturePojo(WeatherDto.class);
    private static final List<WeatherDto> WEATHER_DTO_LIST = List.of(WEATHER_DTO);
    private WeatherMapper weatherMapper = new WeatherMapper();

    @Test
    void testToEntity() {
        var weather = weatherMapper.toEntity(WEATHER_DTO);
        assertThat(weather).isNotNull();
        assertEquals(weather.getParameters(), WEATHER_DTO.getMain());
        assertEquals(weather.getDescription(), WEATHER_DTO.getDescription());
    }

    @Test
    void testToEntityList() {
        var weathers = weatherMapper.toEntityList(WEATHER_DTO_LIST);
        var weatherParametersList = weathers.stream().map(Weather::getParameters).toList();
        var weatherDtoParametersList = WEATHER_DTO_LIST.stream().map(WeatherDto::getMain).toList();
        var weatherDescriptionList = weathers.stream().map(Weather::getDescription).toList();
        var weatherDtoDescriptionList = WEATHER_DTO_LIST.stream().map(WeatherDto::getDescription).toList();
        assertThat(weathers).isNotNull();
        assertEquals(weatherParametersList, weatherDtoParametersList);
        assertEquals(weatherDescriptionList, weatherDtoDescriptionList);
    }
}