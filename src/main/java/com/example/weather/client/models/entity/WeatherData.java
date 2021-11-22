package com.example.weather.client.models.entity;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.UUID;

@Data
@Accessors(chain = true)
@Document
public class WeatherData {
    @Id
    private UUID id;
    @DBRef
    private GeoCoordinates coordinates;
    @DBRef
    private List<Weather> weathers;
    @DBRef
    private Conditions conditions;
    @DBRef
    private Wind wind;
    private Long unixTime;
    private String cityName;
}
