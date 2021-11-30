package com.example.weather.client.client;

import com.example.weather.client.models.dto.WeatherDataDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import static com.example.weather.client.utility.PodamUtility.makePojo;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class WeatherClientTest {

    private static final String URL = "http://api.openweathermap.org/data/2.5/weather";

    @Mock
    private RestTemplate restTemplate;
    @InjectMocks
    private WeatherClient weatherClient;

    @BeforeEach
    void setUp() {
        ReflectionTestUtils.setField(weatherClient, "url", URL);
        ReflectionTestUtils.setField(weatherClient, "keyValue", "123");
    }

    @Test
    void testGetWeather() {
        var weatherDataDto = makePojo(WeatherDataDto.class);
        when(restTemplate.getForObject(eq(URL + "?q=Warsaw&appid=123"), eq(WeatherDataDto.class)))
                .thenReturn(weatherDataDto);
        var weatherDto = weatherClient.getWeather("Warsaw");
        assertThat(weatherDto).isNotNull();
        assertThat(weatherDto).isEqualTo(weatherDataDto);
    }
}