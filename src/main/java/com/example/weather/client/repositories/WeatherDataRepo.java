package com.example.weather.client.repositories;

import com.example.weather.client.models.entity.WeatherData;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface WeatherDataRepo extends MongoRepository<WeatherData, String> {
}
