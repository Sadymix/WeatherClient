package com.example.weather.client.repositories;

import com.example.weather.client.models.entity.WeatherData;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface WeatherDataRepo extends MongoRepository<WeatherData, UUID> {
}
