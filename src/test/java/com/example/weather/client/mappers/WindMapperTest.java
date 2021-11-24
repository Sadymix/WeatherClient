package com.example.weather.client.mappers;

import com.example.weather.client.models.dto.WindDto;
import com.example.weather.client.models.entity.Wind;
import org.junit.jupiter.api.Test;

import static com.example.weather.client.utility.PodamUtility.makePojo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class WindMapperTest {

    private WindDto expectedWindDto = makePojo(WindDto.class);
    private Wind expectedWind = makePojo(Wind.class);
    private final WindMapper windMapper = new WindMapper();

    @Test
    void testToEntity() {
        var wind = windMapper.toEntity(expectedWindDto);
        assertNotNull(wind);
        assertEquals(expectedWindDto.getSpeed(), wind.getSpeed());
        assertEquals(expectedWindDto.getDeg(), wind.getDegrees());
    }

    @Test
    void testToDto() {
        var windDto = windMapper.toDto(expectedWind);
        assertNotNull(windDto);
        assertEquals(expectedWind.getSpeed(), windDto.getSpeed());
        assertEquals(expectedWind.getDegrees(), windDto.getDeg());
    }
}