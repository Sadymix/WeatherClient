package com.example.weather.client.mappers;

import com.example.weather.client.models.dto.WindDto;
import org.junit.jupiter.api.Test;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class WindMapperTest {

    private static PodamFactory FACTORY = new PodamFactoryImpl();
    private static final WindDto WIND_DTO = FACTORY.manufacturePojo(WindDto.class);
    private WindMapper windMapper = new WindMapper();

    @Test
    void testToEntity() {
        var wind = windMapper.toEntity(WIND_DTO);
        assertThat(wind).isNotNull();
        assertEquals(wind.getSpeed(), WIND_DTO.getSpeed());
        assertEquals(wind.getDegrees(), WIND_DTO.getDeg());
    }
}