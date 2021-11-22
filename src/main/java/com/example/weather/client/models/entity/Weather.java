package com.example.weather.client.models.entity;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Data
@Accessors(chain = true)
@Document
public class Weather {
    @Id
    private UUID id;
    private String parameters;
    private String description;
}
