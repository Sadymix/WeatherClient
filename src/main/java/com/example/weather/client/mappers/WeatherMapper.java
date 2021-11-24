package com.example.weather.client.mappers;

import com.example.weather.client.models.dto.WeatherDto;
import com.example.weather.client.models.entity.Weather;
import org.springframework.stereotype.Component;

@Component
public class WeatherMapper {

    public Weather toEntity(WeatherDto weatherDto) {
        return new Weather()
                .setParameters(weatherDto.getMain())
                .setDescription(weatherDto.getDescription());
    }

    public WeatherDto toDto(Weather weather) {
        return new WeatherDto()
                .setMain(weather.getParameters())
                .setDescription(weather.getDescription());
    }
}
