package com.example.weather.client.mappers;

import com.example.weather.client.models.dto.WindDto;
import com.example.weather.client.models.entity.Wind;
import org.springframework.stereotype.Component;

@Component
public class WindMapper {

    public Wind toEntity(WindDto windDto) {
        return new Wind()
                .setSpeed(windDto.getSpeed())
                .setDegrees(windDto.getDeg());
    }
}
