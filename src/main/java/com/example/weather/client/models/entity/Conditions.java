package com.example.weather.client.models.entity;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Accessors(chain = true)
@Document
public class Conditions {
    @Id
    private String id;
    private Double temperature;
    private Double maxTemperature;
    private Double minTemperature;
    private Long pressure;
    private Long humidity;
}
