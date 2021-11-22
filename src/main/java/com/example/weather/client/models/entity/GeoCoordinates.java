package com.example.weather.client.models.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GeoCoordinates {
    private Double lon;
    private Double lat;
}
