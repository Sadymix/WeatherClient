package com.example.weather.client.mappers;

import com.example.weather.client.models.dto.ConditionsDto;
import com.example.weather.client.models.entity.Conditions;
import org.springframework.stereotype.Component;

@Component
public class ConditionsMapper {

    public Conditions toEntity(ConditionsDto conditionsDto) {
        return new Conditions()
                .setTemperature(conditionsDto.getTemp())
                .setMaxTemperature(conditionsDto.getTempMax())
                .setMinTemperature(conditionsDto.getTempMin())
                .setPressure(conditionsDto.getPressure())
                .setHumidity(conditionsDto.getHumidity());
    }

    public ConditionsDto toDto(Conditions conditions) {
        return new ConditionsDto()
                .setTemp(conditions.getTemperature())
                .setTempMax(conditions.getMaxTemperature())
                .setTempMin(conditions.getMinTemperature())
                .setPressure(conditions.getPressure())
                .setHumidity(conditions.getHumidity());
    }
}
