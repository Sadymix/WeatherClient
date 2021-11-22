package com.example.weather.client.repositories;

import com.example.weather.client.models.dto.WeatherDto;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface WeatherRepo extends MongoRepository<WeatherDto, UUID> {
}
