package com.example.weather.client.mappers;

import com.example.weather.client.models.dto.GeoCoordinatesDto;
import org.junit.jupiter.api.Test;

import static com.example.weather.client.utility.PodamUtility.makePojo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class GeoCoordinatesMapperTest {

    private static final GeoCoordinatesDto GEO_COORDINATES_DTO = makePojo(GeoCoordinatesDto.class);
    private final GeoCoordinatesMapper geoCoordinatesMapper = new GeoCoordinatesMapper();


    @Test
    void testToEntity() {
        var geoCoordinates = geoCoordinatesMapper.toEntity(GEO_COORDINATES_DTO);
        assertNotEquals(null, geoCoordinates);
        assertEquals( GEO_COORDINATES_DTO.getLon(), geoCoordinates.getLongitude());
        assertEquals( GEO_COORDINATES_DTO.getLat(), geoCoordinates.getLatitude());
    }
}