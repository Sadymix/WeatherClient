package com.example.weather.client.mappers;

import com.example.weather.client.models.dto.WeatherDataDto;
import com.example.weather.client.models.dto.WeatherDto;
import com.example.weather.client.models.entity.Weather;
import com.example.weather.client.models.entity.WeatherData;
import org.junit.jupiter.api.Test;

import static com.example.weather.client.utility.PodamUtility.makePojo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class WeatherDataMapperTest {

    private WeatherDataDto expectedWeatherDataDto = makePojo(WeatherDataDto.class);
    private WeatherData expectedWeatherData = makePojo(WeatherData.class);
    private final WeatherDataMapper weatherDataMapper = new WeatherDataMapper(
            new GeoCoordinatesMapper(),
            new WeatherMapper(),
            new ConditionsMapper(),
            new WindMapper());

    @Test
    void testToEntity() {
        var weatherData = weatherDataMapper.toEntity(expectedWeatherDataDto);
        assertNotNull(weatherData);
        assertEqualsForToEntity(weatherData);
    }

    @Test
    void testToDto() {
        var weatherDataDto = weatherDataMapper.toDto(expectedWeatherData);
        assertNotNull(weatherDataDto);
        assertEqualsForToDto(weatherDataDto);
    }

    private void assertEqualsForToEntity(WeatherData weatherData) {
        assertEquals(expectedWeatherDataDto.getCoord().getLon(), weatherData.getCoordinates().getLongitude());
        assertEquals(expectedWeatherDataDto.getCoord().getLat(), weatherData.getCoordinates().getLatitude());
        assertEquals(expectedWeatherDataDto.getWeather().stream().map(WeatherDto::getMain).toList(),
                weatherData.getWeathers().stream().map(Weather::getParameters).toList());
        assertEquals(expectedWeatherDataDto.getWeather().stream().map(WeatherDto::getDescription).toList(),
                weatherData.getWeathers().stream().map(Weather::getDescription).toList());
        assertEquals(expectedWeatherDataDto.getMain().getTemp(), weatherData.getConditions().getTemperature());
        assertEquals(expectedWeatherDataDto.getMain().getTempMax(), weatherData.getConditions().getMaxTemperature());
        assertEquals(expectedWeatherDataDto.getMain().getTempMin(), weatherData.getConditions().getMinTemperature());
        assertEquals(expectedWeatherDataDto.getMain().getPressure(), weatherData.getConditions().getPressure());
        assertEquals(expectedWeatherDataDto.getMain().getHumidity(), weatherData.getConditions().getHumidity());
        assertEquals(expectedWeatherDataDto.getWind().getSpeed(), weatherData.getWind().getSpeed());
        assertEquals(expectedWeatherDataDto.getWind().getDeg(), weatherData.getWind().getDegrees());
        assertEquals(expectedWeatherDataDto.getUnixTime(), weatherData.getUnixTime());
        assertEquals(expectedWeatherDataDto.getName(), weatherData.getCityName());
    }

    private void assertEqualsForToDto(WeatherDataDto weatherDataDto) {
        assertEquals(expectedWeatherData.getCoordinates().getLongitude(), weatherDataDto.getCoord().getLon());
        assertEquals(expectedWeatherData.getCoordinates().getLatitude(), weatherDataDto.getCoord().getLat());
        assertEquals(expectedWeatherData.getWeathers().stream().map(Weather::getParameters).toList(),
                weatherDataDto.getWeather().stream().map(WeatherDto::getMain).toList());
        assertEquals(expectedWeatherData.getWeathers().stream().map(Weather::getDescription).toList(),
                weatherDataDto.getWeather().stream().map(WeatherDto::getDescription).toList());
        assertEquals(expectedWeatherData.getConditions().getTemperature(), weatherDataDto.getMain().getTemp());
        assertEquals(expectedWeatherData.getConditions().getMaxTemperature(), weatherDataDto.getMain().getTempMax());
        assertEquals(expectedWeatherData.getConditions().getMinTemperature(), weatherDataDto.getMain().getTempMin());
        assertEquals(expectedWeatherData.getConditions().getPressure(), weatherDataDto.getMain().getPressure());
        assertEquals(expectedWeatherData.getConditions().getHumidity(), weatherDataDto.getMain().getHumidity());
        assertEquals(expectedWeatherData.getWind().getSpeed(), weatherDataDto.getWind().getSpeed());
        assertEquals(expectedWeatherData.getWind().getDegrees(), weatherDataDto.getWind().getDeg());
        assertEquals(expectedWeatherData.getUnixTime(), weatherDataDto.getUnixTime());
        assertEquals(expectedWeatherData.getCityName(), weatherDataDto.getName());
    }
}