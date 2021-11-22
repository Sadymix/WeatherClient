package com.example.weather.client.repositories;

import com.example.weather.client.models.dto.WeatherDto;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface WeatherRepo extends MongoRepository<WeatherDto, Long> {
}
