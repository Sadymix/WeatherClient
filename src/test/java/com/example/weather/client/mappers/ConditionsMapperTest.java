package com.example.weather.client.mappers;

import com.example.weather.client.models.dto.ConditionsDto;
import org.junit.jupiter.api.Test;

import static com.example.weather.client.utility.PodamUtility.makePojo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class ConditionsMapperTest {

    private static final ConditionsDto CONDITIONS_DTO = makePojo(ConditionsDto.class);
    private final ConditionsMapper conditionsMapper = new ConditionsMapper();


    @Test
    void testToEntity() {
        var conditions = conditionsMapper.toEntity(CONDITIONS_DTO);
        assertNotEquals(null, conditions);
        assertEquals(CONDITIONS_DTO.getTemp(), conditions.getTemperature());
        assertEquals(CONDITIONS_DTO.getTempMax(), conditions.getMaxTemperature());
        assertEquals(CONDITIONS_DTO.getTempMin(), conditions.getMinTemperature());
        assertEquals(CONDITIONS_DTO.getPressure(), conditions.getPressure());
        assertEquals(CONDITIONS_DTO.getHumidity(), conditions.getHumidity());
    }
}