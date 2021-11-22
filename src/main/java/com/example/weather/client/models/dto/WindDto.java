package com.example.weather.client.models.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class WindDto {
    private Double speed;
    private Long deg;
}
