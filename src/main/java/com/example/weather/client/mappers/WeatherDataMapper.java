package com.example.weather.client.mappers;

import com.example.weather.client.models.dto.WeatherDataDto;
import com.example.weather.client.models.entity.WeatherData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WeatherDataMapper {

    private final GeoCoordinatesMapper geoCoordinatesMapper;
    private final WeatherMapper weatherMapper;
    private final ConditionsMapper conditionsMapper;
    private final WindMapper windMapper;

    public WeatherData toEntity(WeatherDataDto weatherDataDto) {
        return new WeatherData()
                .setCoordinates(geoCoordinatesMapper.toEntity(weatherDataDto.getCoord()))
                .setWeathers(weatherMapper.toEntityList(weatherDataDto.getWeather()))
                .setConditions(conditionsMapper.toEntity(weatherDataDto.getMain()))
                .setWind(windMapper.toEntity(weatherDataDto.getWind()))
                .setUnixTime(weatherDataDto.getUnixTime())
                .setCityName(weatherDataDto.getName());
    }
}
