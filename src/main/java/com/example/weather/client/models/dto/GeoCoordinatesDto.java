package com.example.weather.client.models.dto;

import lombok.Data;
import lombok.experimental.Accessors;


@Data
@Accessors(chain = true)
public class GeoCoordinatesDto {
    private Double lon;
    private Double lat;
}
