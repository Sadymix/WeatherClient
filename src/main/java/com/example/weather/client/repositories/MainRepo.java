package com.example.weather.client.repositories;

import com.example.weather.client.models.dto.ConditionsDto;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MainRepo extends MongoRepository<ConditionsDto, String> {
}
