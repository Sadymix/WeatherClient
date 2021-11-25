package com.example.weather.client.repositories;

import com.example.weather.client.models.entity.WeatherData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface WeatherDataRepo extends MongoRepository<WeatherData, String> {
    Page<WeatherData> findAllByCityName(String city, Pageable pageable);
    List<WeatherData> findAllByUnixTimeGreaterThanAndUnixTimeLessThanEqual(Long fromTime, Long toTime);
}
