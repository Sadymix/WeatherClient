package com.example.weather.client.mappers;

import com.example.weather.client.models.dto.*;
import com.example.weather.client.models.entity.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.example.weather.client.utility.PodamUtility.makePojo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class WeatherDataMapperTest {

    private WeatherDataDto expectedWeatherDataDto = makePojo(WeatherDataDto.class);
    private WeatherData expectedWeatherData = makePojo(WeatherData.class);
    private GeoCoordinates geoCoordinates = makePojo(GeoCoordinates.class);
    private Weather weather = makePojo(Weather.class);
    private Conditions conditions = makePojo(Conditions.class);
    private Wind wind = makePojo(Wind.class);
    private GeoCoordinatesDto geoCoordinatesDto = makePojo(GeoCoordinatesDto.class);
    private WeatherDto weatherDto = makePojo(WeatherDto.class);
    private ConditionsDto conditionsDto = makePojo(ConditionsDto.class);
    private WindDto windDto = makePojo(WindDto.class);

    @Mock
    private GeoCoordinatesMapper geoCoordinatesMapper;
    @Mock
    private WeatherMapper weatherMapper;
    @Mock
    private ConditionsMapper conditionsMapper;
    @Mock
    private WindMapper windMapper;
    @InjectMocks
    private WeatherDataMapper weatherDataMapper;

    @Test
    void testToEntity() {
        when(geoCoordinatesMapper.toEntity(any(GeoCoordinatesDto.class)))
                .thenReturn(geoCoordinates);
        when(weatherMapper.toEntity(any(WeatherDto.class)))
                .thenReturn(weather);
        when(conditionsMapper.toEntity(any(ConditionsDto.class)))
                .thenReturn(conditions);
        when(windMapper.toEntity(any(WindDto.class)))
                .thenReturn(wind);
        var weatherData = weatherDataMapper.toEntity(expectedWeatherDataDto);
        assertNotNull(weatherData);
        assertEqualsForToEntity(weatherData);
    }

    @Test
    void testToDto() {
        when(geoCoordinatesMapper.toDto(any(GeoCoordinates.class)))
                .thenReturn(geoCoordinatesDto);
        when(weatherMapper.toDto(any(Weather.class)))
                .thenReturn(weatherDto);
        when(conditionsMapper.toDto(any(Conditions.class)))
                .thenReturn(conditionsDto);
        when(windMapper.toDto(any(Wind.class)))
                .thenReturn(windDto);
        var weatherDataDto = weatherDataMapper.toDto(expectedWeatherData);
        assertNotNull(weatherDataDto);
        assertEqualsForToDto(weatherDataDto);
    }

    private void assertEqualsForToEntity(WeatherData weatherData) {
        assertEquals(geoCoordinates, weatherData.getCoordinates());
        assertEquals(weather, weatherData.getWeathers().get(0));
        assertEquals(conditions, weatherData.getConditions());
        assertEquals(wind, weatherData.getWind());
        assertEquals(expectedWeatherDataDto.getUnixTime(), weatherData.getUnixTime());
        assertEquals(expectedWeatherDataDto.getName(), weatherData.getCityName());
    }

    private void assertEqualsForToDto(WeatherDataDto weatherDataDto) {
        assertEquals(geoCoordinatesDto, weatherDataDto.getCoord());
        assertEquals(weatherDto, weatherDataDto.getWeather().get(0));
        assertEquals(conditionsDto, weatherDataDto.getMain());
        assertEquals(windDto, weatherDataDto.getWind());
        assertEquals(expectedWeatherData.getUnixTime(), weatherDataDto.getUnixTime());
        assertEquals(expectedWeatherData.getCityName(), weatherDataDto.getName());
    }
}