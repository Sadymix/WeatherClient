package com.example.weather.client.repositories;

import com.example.weather.client.models.dto.WindDto;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface WindRepo extends MongoRepository<WindDto, String> {
}
