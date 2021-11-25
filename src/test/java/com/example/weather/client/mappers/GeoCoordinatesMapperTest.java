package com.example.weather.client.mappers;

import com.example.weather.client.models.dto.GeoCoordinatesDto;
import com.example.weather.client.models.entity.GeoCoordinates;
import org.junit.jupiter.api.Test;

import static com.example.weather.client.utility.PodamUtility.makePojo;
import static org.junit.jupiter.api.Assertions.*;

class GeoCoordinatesMapperTest {

    private final GeoCoordinatesMapper geoCoordinatesMapper = new GeoCoordinatesMapper();

    @Test
    void testToEntity() {
        var expectedGeoCoordinatesDto = makePojo(GeoCoordinatesDto.class);
        var geoCoordinates = geoCoordinatesMapper.toEntity(expectedGeoCoordinatesDto);
        assertNotNull(geoCoordinates);
        assertEquals(expectedGeoCoordinatesDto.getLon(), geoCoordinates.getLongitude());
        assertEquals(expectedGeoCoordinatesDto.getLat(), geoCoordinates.getLatitude());
    }

    @Test
    void testToDto() {
        var expectedGeoCoordinates = makePojo(GeoCoordinates.class);
        var geoCoordinatesDto = geoCoordinatesMapper.toDto(expectedGeoCoordinates);
        assertNotNull(geoCoordinatesDto);
        assertEquals(expectedGeoCoordinates.getLongitude(), geoCoordinatesDto.getLon());
        assertEquals(expectedGeoCoordinates.getLatitude(), geoCoordinatesDto.getLat());
    }
}