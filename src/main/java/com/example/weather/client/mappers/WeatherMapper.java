package com.example.weather.client.mappers;

import com.example.weather.client.models.dto.WeatherDto;
import com.example.weather.client.models.entity.Weather;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface WeatherMapper {

    @Mappings({
            @Mapping(target = "parameters", source = "main")
    })
    Weather toEntity(WeatherDto weatherDto);

    @InheritInverseConfiguration(name = "toEntity")
    WeatherDto toDto(Weather weather);

    List<WeatherDto> mapToWeatherDtoList(List<Weather> weatherList);
}
