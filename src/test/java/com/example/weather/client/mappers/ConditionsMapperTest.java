package com.example.weather.client.mappers;

import com.example.weather.client.models.dto.ConditionsDto;
import com.example.weather.client.models.entity.Conditions;
import org.junit.jupiter.api.Test;


import static com.example.weather.client.utility.PodamUtility.makePojo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ConditionsMapperTest {

    private final ConditionsMapper conditionsMapper = new ConditionsMapper();

    @Test
    void testToEntity() {
        var expectedConditionsDto = makePojo(ConditionsDto.class);
        var conditions = conditionsMapper.toEntity(expectedConditionsDto);
        assertNotNull(conditions);
        assertEquals(expectedConditionsDto.getTemp(), conditions.getTemperature());
        assertEquals(expectedConditionsDto.getTempMax(), conditions.getMaxTemperature());
        assertEquals(expectedConditionsDto.getTempMin(), conditions.getMinTemperature());
        assertEquals(expectedConditionsDto.getPressure(), conditions.getPressure());
        assertEquals(expectedConditionsDto.getHumidity(), conditions.getHumidity());
    }

    @Test
    void testToDto() {
        var expectedConditions = makePojo(Conditions.class);
        var conditionsDto = conditionsMapper.toDto(expectedConditions);
        assertNotNull(conditionsDto);
        assertEquals(expectedConditions.getTemperature(), conditionsDto.getTemp());
        assertEquals(expectedConditions.getMaxTemperature(), conditionsDto.getTempMax());
        assertEquals(expectedConditions.getMinTemperature(), conditionsDto.getTempMin());
        assertEquals(expectedConditions.getPressure(), conditionsDto.getPressure());
        assertEquals(expectedConditions.getHumidity(), conditionsDto.getHumidity());
    }
}