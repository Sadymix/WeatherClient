package com.example.weather.client.services;

import com.example.weather.client.client.WeatherClient;
import com.example.weather.client.mappers.WeatherDataMapper;
import com.example.weather.client.models.dto.WeatherDataDto;
import com.example.weather.client.models.entity.WeatherData;
import com.example.weather.client.repositories.WeatherDataRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class WeatherDataServiceTest {

    private static PodamFactory FACTORY = new PodamFactoryImpl();
    private static final WeatherDataDto WEATHER_DATA_DTO = FACTORY.manufacturePojo(WeatherDataDto.class);
    private static final WeatherData WEATHER_DATA = FACTORY.manufacturePojo(WeatherData.class);

    @Mock
    private WeatherDataRepo weatherDataRepo;
    @Mock
    private WeatherClient weatherClient;
    @Mock
    private WeatherDataMapper weatherDataMapper;
    @InjectMocks
    private WeatherDataService weatherDataService;

    @Test
    void testSaveWeatherDataForWarsaw() {
        when(weatherClient.getWeather(anyString())).thenReturn(WEATHER_DATA_DTO);
        when(weatherDataMapper.toEntity(WEATHER_DATA_DTO)).thenReturn(WEATHER_DATA);
        when(weatherDataRepo.save(WEATHER_DATA)).thenReturn(WEATHER_DATA);
        weatherDataService.saveWeatherDataForWarsaw();
        verify(weatherClient).getWeather(anyString());
        verify(weatherDataMapper).toEntity(WEATHER_DATA_DTO);
        verify(weatherDataRepo).save(WEATHER_DATA);
    }
}