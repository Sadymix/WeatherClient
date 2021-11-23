package com.example.weather.client.mappers;

import com.example.weather.client.models.dto.WindDto;
import org.junit.jupiter.api.Test;

import static com.example.weather.client.utility.PodamUtility.makePojo;
import static org.junit.jupiter.api.Assertions.*;

class WindMapperTest {

    private WindDto windDto = makePojo(WindDto.class);
    private final WindMapper windMapper = new WindMapper();

    @Test
    void testToEntity() {
        var wind = windMapper.toEntity(windDto);
        assertNotNull(wind);
        assertEquals(windDto.getSpeed(), wind.getSpeed());
        assertEquals(windDto.getDeg(), wind.getDegrees());
    }
}