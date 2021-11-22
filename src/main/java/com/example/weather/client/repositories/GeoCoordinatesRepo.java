package com.example.weather.client.repositories;

import com.example.weather.client.models.dto.GeoCoordinatesDto;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface GeoCoordinatesRepo extends MongoRepository<GeoCoordinatesDto, UUID> {
}
