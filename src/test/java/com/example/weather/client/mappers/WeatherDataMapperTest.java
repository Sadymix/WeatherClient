package com.example.weather.client.mappers;

import com.example.weather.client.models.dto.WeatherDataDto;
import com.example.weather.client.models.dto.WeatherDto;
import com.example.weather.client.models.entity.Weather;
import com.example.weather.client.models.entity.WeatherData;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;


@ExtendWith(MockitoExtension.class)
class WeatherDataMapperTest {

    private static PodamFactory FACTORY = new PodamFactoryImpl();
    private static final WeatherDataDto WEATHER_DATA_DTO = FACTORY.manufacturePojo(WeatherDataDto.class);

    private GeoCoordinatesMapper geoCoordinatesMapper = new GeoCoordinatesMapper();
    private WeatherMapper weatherMapper = new WeatherMapper();
    private ConditionsMapper conditionsMapper = new ConditionsMapper();
    private WindMapper windMapper = new WindMapper();
    private WeatherDataMapper weatherDataMapper =
            new WeatherDataMapper(geoCoordinatesMapper, weatherMapper, conditionsMapper, windMapper);

    @Test
    void testToEntity() {
        var weatherData = weatherDataMapper.toEntity(WEATHER_DATA_DTO);
        assertThat(weatherData).isNotNull();
        assertEqualsForWeatherData(weatherData);
    }

    private void assertEqualsForWeatherData(WeatherData weatherData) {
        assertEquals(weatherData.getCoordinates().getLongitude(), WEATHER_DATA_DTO.getCoord().getLon());
        assertEquals(weatherData.getCoordinates().getLatitude(), WEATHER_DATA_DTO.getCoord().getLat());
        assertEquals(weatherData.getWeathers().stream().map(Weather::getParameters).toList(),
                WEATHER_DATA_DTO.getWeather().stream().map(WeatherDto::getMain).toList());
        assertEquals(weatherData.getWeathers().stream().map(Weather::getDescription).toList(),
                WEATHER_DATA_DTO.getWeather().stream().map(WeatherDto::getDescription).toList());
        assertEquals(weatherData.getConditions().getTemperature(), WEATHER_DATA_DTO.getMain().getTemp());
        assertEquals(weatherData.getConditions().getMaxTemperature(), WEATHER_DATA_DTO.getMain().getTempMax());
        assertEquals(weatherData.getConditions().getMinTemperature(), WEATHER_DATA_DTO.getMain().getTempMin());
        assertEquals(weatherData.getConditions().getPressure(), WEATHER_DATA_DTO.getMain().getPressure());
        assertEquals(weatherData.getConditions().getHumidity(), WEATHER_DATA_DTO.getMain().getHumidity());
        assertEquals(weatherData.getWind().getSpeed(), WEATHER_DATA_DTO.getWind().getSpeed());
        assertEquals(weatherData.getWind().getDegrees(), WEATHER_DATA_DTO.getWind().getDeg());
        assertEquals(weatherData.getUnixTime(), WEATHER_DATA_DTO.getUnixTime());
        assertEquals(weatherData.getCityName(), WEATHER_DATA_DTO.getName());
    }
}