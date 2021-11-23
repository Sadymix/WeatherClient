package com.example.weather.client.mappers;

import com.example.weather.client.models.dto.ConditionsDto;
import org.junit.jupiter.api.Test;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ConditionsMapperTest {

    private static PodamFactory FACTORY = new PodamFactoryImpl();
    private static final ConditionsDto CONDITIONS_DTO = FACTORY.manufacturePojo(ConditionsDto.class);
    private ConditionsMapper conditionsMapper = new ConditionsMapper();


    @Test
    void testToEntity() {
        var conditions = conditionsMapper.toEntity(CONDITIONS_DTO);
        assertThat(conditions).isNotNull();
        assertEquals(conditions.getTemperature(), CONDITIONS_DTO.getTemp());
        assertEquals(conditions.getMaxTemperature(), CONDITIONS_DTO.getTempMax());
        assertEquals(conditions.getMinTemperature(), CONDITIONS_DTO.getTempMin());
        assertEquals(conditions.getPressure(), CONDITIONS_DTO.getPressure());
        assertEquals(conditions.getHumidity(), CONDITIONS_DTO.getHumidity());
    }
}