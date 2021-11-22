package com.example.weather.client.models.dto;

import lombok.Data;
import lombok.experimental.Accessors;


@Data
@Accessors(chain = true)
public class ConditionsDto {
    private Double temp;
    private Double tempMin;
    private Double tempMax;
    private Long pressure;
    private Long humidity;
}
