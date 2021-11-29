package com.example.weather.client.mappers;

import com.example.weather.client.models.dto.GeoCoordinatesDto;
import com.example.weather.client.models.entity.GeoCoordinates;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface GeoCoordinatesMapper {
     @Mappings({
             @Mapping(target = "longitude", source = "lon"),
             @Mapping(target = "latitude", source = "lat")
     })
     GeoCoordinates toEntity(GeoCoordinatesDto geoCoordinatesDto);

     @InheritInverseConfiguration(name = "toEntity")
     GeoCoordinatesDto toDto(GeoCoordinates geoCoordinates);
}
