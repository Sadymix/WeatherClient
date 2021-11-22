package com.example.weather.client.models.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Main {
    private Double temp;
    private Double tempMin;
    private Double tempMax;
    private Long pressure;
    private Long humidity;
}
