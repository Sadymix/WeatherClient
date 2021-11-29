package com.example.weather.client.mappers;

import com.example.weather.client.models.dto.ConditionsDto;
import com.example.weather.client.models.entity.Conditions;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface ConditionsMapper {
    @Mappings({
            @Mapping(target = "temperature", source = "conditionsDto.temp"),
            @Mapping(target = "maxTemperature", source = "conditionsDto.tempMax"),
            @Mapping(target = "minTemperature", source = "conditionsDto.tempMin")

    })
    Conditions toEntity(ConditionsDto conditionsDto);

    @InheritInverseConfiguration(name = "toEntity")
    ConditionsDto toDto(Conditions conditions);

}
