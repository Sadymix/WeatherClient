package com.example.weather.client.mappers;

import com.example.weather.client.models.dto.WeatherDataDto;
import com.example.weather.client.models.entity.WeatherData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(MockitoExtension.class)
class WeatherDataMapperTest {

    private static PodamFactory FACTORY = new PodamFactoryImpl();
    private static final WeatherDataDto WEATHER_DATA_DTO = FACTORY.manufacturePojo(WeatherDataDto.class);
    private static WeatherData WEATHER_DATA;

    private GeoCoordinatesMapper geoCoordinatesMapper = new GeoCoordinatesMapper();
    private WeatherMapper weatherMapper = new WeatherMapper();
    private ConditionsMapper conditionsMapper = new ConditionsMapper();
    private WindMapper windMapper = new WindMapper();
    private WeatherDataMapper weatherDataMapper =
            new WeatherDataMapper(geoCoordinatesMapper, weatherMapper, conditionsMapper, windMapper);


    @BeforeEach
    void setUp() {
        WEATHER_DATA = new WeatherData()
                .setCoordinates(geoCoordinatesMapper.toEntity(WEATHER_DATA_DTO.getCoord()))
                .setWeathers(weatherMapper.toEntityList(WEATHER_DATA_DTO.getWeather()))
                .setConditions(conditionsMapper.toEntity(WEATHER_DATA_DTO.getMain()))
                .setWind(windMapper.toEntity(WEATHER_DATA_DTO.getWind()))
                .setUnixTime(WEATHER_DATA_DTO.getUnixTime())
                .setCityName(WEATHER_DATA_DTO.getName());
    }

    @Test
    void testToEntity() {
        var weatherData = weatherDataMapper.toEntity(WEATHER_DATA_DTO);
        assertThat(weatherData).isNotNull();
        assertThat(weatherData).isEqualTo(WEATHER_DATA);
    }
}