package com.example.weather.client.mappers;

import com.example.weather.client.models.dto.WeatherDataDto;
import com.example.weather.client.models.dto.WeatherDto;
import com.example.weather.client.models.entity.Weather;
import com.example.weather.client.models.entity.WeatherData;
import org.junit.jupiter.api.Test;

import static com.example.weather.client.utility.PodamUtility.makePojo;
import static org.junit.jupiter.api.Assertions.*;

class WeatherDataMapperTest {

    private WeatherDataDto weatherDataDto = makePojo(WeatherDataDto.class);
    private final WeatherDataMapper weatherDataMapper = new WeatherDataMapper(
            new GeoCoordinatesMapper(),
            new WeatherMapper(),
            new ConditionsMapper(),
            new WindMapper());

    @Test
    void testToEntity() {
        var weatherData = weatherDataMapper.toEntity(weatherDataDto);
        assertNotNull(weatherData);
        assertEqualsForWeatherData(weatherData);
    }

    private void assertEqualsForWeatherData(WeatherData weatherData) {
        assertEquals(weatherDataDto.getCoord().getLon(), weatherData.getCoordinates().getLongitude());
        assertEquals(weatherDataDto.getCoord().getLat(), weatherData.getCoordinates().getLatitude());
        assertEquals(weatherDataDto.getWeather().stream().map(WeatherDto::getMain).toList(),
                weatherData.getWeathers().stream().map(Weather::getParameters).toList());
        assertEquals(weatherDataDto.getWeather().stream().map(WeatherDto::getDescription).toList(),
                weatherData.getWeathers().stream().map(Weather::getDescription).toList());
        assertEquals(weatherDataDto.getMain().getTemp(), weatherData.getConditions().getTemperature());
        assertEquals(weatherDataDto.getMain().getTempMax(), weatherData.getConditions().getMaxTemperature());
        assertEquals(weatherDataDto.getMain().getTempMin(), weatherData.getConditions().getMinTemperature());
        assertEquals(weatherDataDto.getMain().getPressure(), weatherData.getConditions().getPressure());
        assertEquals(weatherDataDto.getMain().getHumidity(), weatherData.getConditions().getHumidity());
        assertEquals(weatherDataDto.getWind().getSpeed(), weatherData.getWind().getSpeed());
        assertEquals(weatherDataDto.getWind().getDeg(), weatherData.getWind().getDegrees());
        assertEquals(weatherDataDto.getUnixTime(), weatherData.getUnixTime());
        assertEquals(weatherDataDto.getName(), weatherData.getCityName());
    }
}