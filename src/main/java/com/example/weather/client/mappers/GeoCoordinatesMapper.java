package com.example.weather.client.mappers;

import com.example.weather.client.models.dto.GeoCoordinatesDto;
import com.example.weather.client.models.entity.GeoCoordinates;
import org.springframework.stereotype.Component;

@Component
public class GeoCoordinatesMapper {

    public GeoCoordinates toEntity(GeoCoordinatesDto geoCoordinatesDto) {
        return new GeoCoordinates()
                .setLongitude(geoCoordinatesDto.getLon())
                .setLatitude(geoCoordinatesDto.getLat());
    }
}
