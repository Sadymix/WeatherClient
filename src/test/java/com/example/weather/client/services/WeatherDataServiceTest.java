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

import java.util.List;

import static com.example.weather.client.utility.PodamUtility.makePojo;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class WeatherDataServiceTest {

    private WeatherDataDto weatherDataDto = makePojo(WeatherDataDto.class);
    private WeatherData weatherData = makePojo(WeatherData.class);
    private List<WeatherData> weatherDataList = List.of(weatherData);

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
        when(weatherClient.getWeather(anyString())).thenReturn(weatherDataDto);
        when(weatherDataMapper.toEntity(weatherDataDto)).thenReturn(weatherData);
        when(weatherDataRepo.save(weatherData)).thenReturn(weatherData);
        weatherDataService.saveWeatherDataForWarsaw();
        verify(weatherClient).getWeather(anyString());
        verify(weatherDataMapper).toEntity(weatherDataDto);
        verify(weatherDataRepo).save(weatherData);
    }

    @Test
    void testDeleteWeatherDataInTimePeriod() {
        when(weatherDataRepo.findAllByUnixTimeGreaterThanAndUnixTimeLessThanEqual(11L, 12L))
                .thenReturn(weatherDataList);
        doNothing().when(weatherDataRepo).deleteAll(weatherDataList);
        weatherDataService.deleteWeatherDataInTimePeriod(11L, 12L);
        verify(weatherDataRepo).findAllByUnixTimeGreaterThanAndUnixTimeLessThanEqual(11L, 12L);
        verify(weatherDataRepo).deleteAll(weatherDataList);
    }
}