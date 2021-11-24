package com.example.weather.client.mappers;

import com.example.weather.client.models.dto.WeatherDto;
import com.example.weather.client.models.entity.Weather;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class WeatherMapper {

    public Weather toEntity(WeatherDto weatherDto) {
        return new Weather()
                .setParameters(weatherDto.getMain())
                .setDescription(weatherDto.getDescription());
    }

    public List<Weather> toEntityList(List<WeatherDto> weatherDtos) {
        return weatherDtos.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }

    public List<WeatherDto> toDtoList(List<Weather> weatherList) {
        return weatherList.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    private WeatherDto toDto(Weather weather) {
        return new WeatherDto()
                .setMain(weather.getParameters())
                .setDescription(weather.getDescription());
    }
}
