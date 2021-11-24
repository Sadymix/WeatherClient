package com.example.weather.client.mappers;

import com.example.weather.client.models.dto.WeatherDataDto;
import com.example.weather.client.models.entity.WeatherData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

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

    public WeatherDataDto toDto(WeatherData weatherData) {
        return new WeatherDataDto()
                .setCoord(geoCoordinatesMapper.toDto(weatherData.getCoordinates()))
                .setWeather(weatherMapper.toDtoList(weatherData.getWeathers()))
                .setMain(conditionsMapper.toDto(weatherData.getConditions()))
                .setWind(windMapper.toDto(weatherData.getWind()))
                .setUnixTime(weatherData.getUnixTime())
                .setName(weatherData.getCityName());
    }

    public List<WeatherDataDto> toDtoList(List<WeatherData> weatherData) {
        return weatherData.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}
