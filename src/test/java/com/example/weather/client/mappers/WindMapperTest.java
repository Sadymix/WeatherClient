package com.example.weather.client.mappers;

import com.example.weather.client.models.dto.WindDto;
import org.junit.jupiter.api.Test;

import static com.example.weather.client.utility.PodamUtility.makePojo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class WindMapperTest {

    private static final WindDto WIND_DTO = makePojo(WindDto.class);
    private final WindMapper windMapper = new WindMapper();

    @Test
    void testToEntity() {
        var wind = windMapper.toEntity(WIND_DTO);
        assertNotEquals(null, wind);
        assertEquals(WIND_DTO.getSpeed(), wind.getSpeed());
        assertEquals(WIND_DTO.getDeg(), wind.getDegrees());
    }
}