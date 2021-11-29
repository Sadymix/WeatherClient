package com.example.weather.client.mappers;

import com.example.weather.client.models.dto.WeatherDataDto;
import com.example.weather.client.models.entity.WeatherData;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring", uses = {
        ConditionsMapper.class,
        GeoCoordinatesMapper.class,
        WeatherMapper.class,
        WindMapper.class})
public interface WeatherDataMapper {

    @Mappings({
            @Mapping(target = "coordinates", source = "coord"),
            @Mapping(target = "weathers", source = "weather"),
            @Mapping(target = "conditions", source = "main"),
            @Mapping(target = "cityName", source = "name")
    })
    WeatherData toEntity(WeatherDataDto weatherDataDto);

    @InheritInverseConfiguration(name = "toEntity")
    WeatherDataDto toDto(WeatherData weatherData);

    List<WeatherDataDto> mapToDtoList(List<WeatherData> weatherDataPage);
}
