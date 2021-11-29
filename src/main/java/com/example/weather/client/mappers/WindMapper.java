package com.example.weather.client.mappers;

import com.example.weather.client.models.dto.WindDto;
import com.example.weather.client.models.entity.Wind;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface WindMapper {
    @Mappings({
            @Mapping(target = "degrees", source = "deg")
    })
    Wind toEntity(WindDto windDto);
    @InheritInverseConfiguration(name = "toEntity")
    WindDto toDto(Wind wind);
}
