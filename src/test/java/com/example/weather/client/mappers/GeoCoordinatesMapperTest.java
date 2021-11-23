package com.example.weather.client.mappers;

import com.example.weather.client.models.dto.GeoCoordinatesDto;
import org.junit.jupiter.api.Test;

import static com.example.weather.client.utility.PodamUtility.makePojo;
import static org.junit.jupiter.api.Assertions.*;

class GeoCoordinatesMapperTest {

    private GeoCoordinatesDto geoCoordinatesDto = makePojo(GeoCoordinatesDto.class);
    private final GeoCoordinatesMapper geoCoordinatesMapper = new GeoCoordinatesMapper();

    @Test
    void testToEntity() {
        var geoCoordinates = geoCoordinatesMapper.toEntity(geoCoordinatesDto);
        assertNotNull(geoCoordinates);
        assertEquals(geoCoordinatesDto.getLon(), geoCoordinates.getLongitude());
        assertEquals(geoCoordinatesDto.getLat(), geoCoordinates.getLatitude());
    }
}