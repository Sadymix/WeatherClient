package com.example.weather.client.models.entity;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Accessors(chain = true)
@Document
public class Wind {
    @Id
    private String id;
    private Double speed;
    private Long degrees;
}
