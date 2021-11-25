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
import org.springframework.data.domain.*;

import java.util.List;

import static com.example.weather.client.utility.PodamUtility.makePojo;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class WeatherDataServiceTest {

    private static final WeatherDataDto WEATHER_DATA_DTO = makePojo(WeatherDataDto.class);
    private static final WeatherData WEATHER_DATA = makePojo(WeatherData.class);
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

    @Test
    void testGetWeatherDataByCity() {
        Page<WeatherData> pageWeatherData = new PageImpl<>(List.of(WEATHER_DATA));
        Pageable pageable = PageRequest.of(0,5, Sort.by("unixTime").ascending());
        when(weatherDataRepo.findAllByCityName("Berlin", pageable))
                .thenReturn(pageWeatherData);
        when(weatherDataMapper.toDto(WEATHER_DATA)).thenReturn(WEATHER_DATA_DTO);

        var berlinWeatherDataDto =
                weatherDataService.getWeatherDataByCity("Berlin", 0, 5);
        verify(weatherDataRepo).findAllByCityName("Berlin", pageable);
        verify(weatherDataMapper).toDto(WEATHER_DATA);
        assertThat(berlinWeatherDataDto).isNotNull();
        assertThat(berlinWeatherDataDto.getContent()).isEqualTo(List.of(WEATHER_DATA_DTO));
    }
}