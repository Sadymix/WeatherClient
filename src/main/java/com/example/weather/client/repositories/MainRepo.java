package com.example.weather.client.repositories;

import com.example.weather.client.models.dto.ConditionsDto;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface MainRepo extends MongoRepository<ConditionsDto, UUID> {
}
