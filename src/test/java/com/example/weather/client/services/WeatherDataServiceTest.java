package com.example.weather.client.services;

import com.example.weather.client.client.WeatherClient;
import com.example.weather.client.exceptions.ResourceToLargeException;
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

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

import static com.example.weather.client.utility.PodamUtility.makePojo;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class WeatherDataServiceTest {

    private static final WeatherDataDto WEATHER_DATA_DTO = makePojo(WeatherDataDto.class);
    private static final WeatherData WEATHER_DATA = makePojo(WeatherData.class);
    private static final Page<WeatherData> PAGE_WEATHER_DATA = new PageImpl<>(List.of(WEATHER_DATA));
    private List<WeatherData> WEATHER_DATA_LIST = List.of(WEATHER_DATA);
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
    void testDeleteWeatherDataInTimePeriod() {
        var now = LocalDateTime.now();
        var seconds = now.toEpochSecond(ZoneOffset.UTC);
        when(weatherDataRepo.findAllByUnixTimeGreaterThanAndUnixTimeLessThanEqual(seconds, seconds))
                .thenReturn(WEATHER_DATA_LIST);
        doNothing().when(weatherDataRepo).deleteAll(WEATHER_DATA_LIST);
        weatherDataService.deleteWeatherDataInTimePeriod(now, now);
        verify(weatherDataRepo).findAllByUnixTimeGreaterThanAndUnixTimeLessThanEqual(seconds, seconds);
        verify(weatherDataRepo).deleteAll(WEATHER_DATA_LIST);
    }

    @Test
    void testGetWeatherDataByCity() {
        Pageable pageable = PageRequest.of(0, 5, Sort.by("unixTime").ascending());
        when(weatherDataRepo.findAllByCityName("Berlin", pageable))
                .thenReturn(PAGE_WEATHER_DATA);
        when(weatherDataMapper.toDto(WEATHER_DATA)).thenReturn(WEATHER_DATA_DTO);

        var berlinWeatherDataDto =
                weatherDataService.getWeatherDataByCity("Berlin", 0, 5);
        verify(weatherDataRepo).findAllByCityName("Berlin", pageable);
        verify(weatherDataMapper).toDto(WEATHER_DATA);
        assertThat(berlinWeatherDataDto).isNotNull();
        assertThat(berlinWeatherDataDto.getContent()).isEqualTo(List.of(WEATHER_DATA_DTO));
    }

    @Test
    void testGetWeatherDataByCityWithToLargeSize() {
        Pageable pageable = PageRequest.of(0, 150, Sort.by("unixTime").ascending());
        assertThrows(ResourceToLargeException.class,
                () -> weatherDataService.getWeatherDataByCity("Berlin", 0, 150));
        verify(weatherDataRepo, times(0)).findAllByCityName("Berlin", pageable);
    }
}