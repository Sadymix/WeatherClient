package com.example.weather.client.mappers;

import com.example.weather.client.models.dto.WeatherDataDto;
import com.example.weather.client.models.dto.WeatherDto;
import com.example.weather.client.models.entity.Weather;
import com.example.weather.client.models.entity.WeatherData;
import org.junit.jupiter.api.Test;

import static com.example.weather.client.utility.PodamUtility.makePojo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class WeatherDataMapperTest {

    private static final WeatherDataDto WEATHER_DATA_DTO = makePojo(WeatherDataDto.class);

    private WeatherDataMapper weatherDataMapper = new WeatherDataMapper(
            new GeoCoordinatesMapper(),
            new WeatherMapper(),
            new ConditionsMapper(),
            new WindMapper());

    @Test
    void testToEntity() {
        var weatherData = weatherDataMapper.toEntity(WEATHER_DATA_DTO);
        assertNotEquals(null, weatherData);
        assertEqualsForWeatherData(weatherData);
    }

    private void assertEqualsForWeatherData(WeatherData weatherData) {
        assertEquals(WEATHER_DATA_DTO.getCoord().getLon(), weatherData.getCoordinates().getLongitude());
        assertEquals(WEATHER_DATA_DTO.getCoord().getLat(), weatherData.getCoordinates().getLatitude());
        assertEquals(WEATHER_DATA_DTO.getWeather().stream().map(WeatherDto::getMain).toList(),
                weatherData.getWeathers().stream().map(Weather::getParameters).toList());
        assertEquals(WEATHER_DATA_DTO.getWeather().stream().map(WeatherDto::getDescription).toList(),
                weatherData.getWeathers().stream().map(Weather::getDescription).toList());
        assertEquals(WEATHER_DATA_DTO.getMain().getTemp(), weatherData.getConditions().getTemperature());
        assertEquals(WEATHER_DATA_DTO.getMain().getTempMax(), weatherData.getConditions().getMaxTemperature());
        assertEquals(WEATHER_DATA_DTO.getMain().getTempMin(), weatherData.getConditions().getMinTemperature());
        assertEquals(WEATHER_DATA_DTO.getMain().getPressure(), weatherData.getConditions().getPressure());
        assertEquals(WEATHER_DATA_DTO.getMain().getHumidity(), weatherData.getConditions().getHumidity());
        assertEquals(WEATHER_DATA_DTO.getWind().getSpeed(), weatherData.getWind().getSpeed());
        assertEquals(WEATHER_DATA_DTO.getWind().getDeg(), weatherData.getWind().getDegrees());
        assertEquals(WEATHER_DATA_DTO.getUnixTime(), weatherData.getUnixTime());
        assertEquals(WEATHER_DATA_DTO.getName(), weatherData.getCityName());
    }
}