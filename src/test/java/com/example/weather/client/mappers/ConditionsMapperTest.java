package com.example.weather.client.mappers;

import com.example.weather.client.models.dto.ConditionsDto;
import org.junit.jupiter.api.Test;


import static com.example.weather.client.utility.PodamUtility.makePojo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ConditionsMapperTest {

    private ConditionsDto conditionsDto = makePojo(ConditionsDto.class);
    private final ConditionsMapper conditionsMapper = new ConditionsMapper();

    @Test
    void testToEntity() {
        var conditions = conditionsMapper.toEntity(conditionsDto);
        assertNotNull(conditions);
        assertEquals(conditionsDto.getTemp(), conditions.getTemperature());
        assertEquals(conditionsDto.getTempMax(), conditions.getMaxTemperature());
        assertEquals(conditionsDto.getTempMin(), conditions.getMinTemperature());
        assertEquals(conditionsDto.getPressure(), conditions.getPressure());
        assertEquals(conditionsDto.getHumidity(), conditions.getHumidity());
    }
}