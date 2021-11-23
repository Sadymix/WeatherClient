package com.example.weather.client.mappers;

import com.example.weather.client.models.dto.GeoCoordinatesDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class GeoCoordinatesMapperTest {

    private static PodamFactory FACTORY = new PodamFactoryImpl();
    private static final GeoCoordinatesDto GEO_COORDINATES_DTO = FACTORY.manufacturePojo(GeoCoordinatesDto.class);
    private GeoCoordinatesMapper geoCoordinatesMapper = new GeoCoordinatesMapper();


    @Test
    void testToEntity() {
        var geoCoordinates = geoCoordinatesMapper.toEntity(GEO_COORDINATES_DTO);
        assertThat(geoCoordinates).isNotNull();
        assertEquals(geoCoordinates.getLongitude(), GEO_COORDINATES_DTO.getLon());
        assertEquals(geoCoordinates.getLatitude(), GEO_COORDINATES_DTO.getLat());
    }
}