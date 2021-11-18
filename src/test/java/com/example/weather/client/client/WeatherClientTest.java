package com.example.weather.client.client;

import com.example.weather.client.models.dto.WeatherDto;
import com.example.weather.client.models.entity.Coord;
import com.example.weather.client.models.entity.Main;
import com.example.weather.client.models.entity.Weather;
import com.example.weather.client.models.entity.Wind;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class WeatherClientTest {

    private static  final String URL = "http://api.openweathermap.org/data/2.5/weather";
    private static final Coord COORD = Coord.builder()
            .lon(213.22)
            .lat(321.11)
            .build();
    private static final Weather WEATHER = Weather.builder()
            .main("main")
            .description("description")
            .build();
    private static final Main MAIN = Main.builder()
            .temp(23.23)
            .tempMin(20.12)
            .tempMax(24.41)
            .pressure(1020L)
            .humidity(100L)
            .build();
    private static final Wind WIND = Wind.builder()
            .speed(24.32)
            .deg(123L)
            .build();
    private static final WeatherDto WEATHER_DTO = WeatherDto.builder()
            .coord(COORD)
            .weather(List.of(WEATHER))
            .main(MAIN)
            .wind(WIND)
            .dt(2132132L)
            .name("Warsaw")
            .build();

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
        when(restTemplate.getForObject(eq(URL+"?q=Warsaw&appid=123"), eq(WeatherDto.class)))
                .thenReturn(WEATHER_DTO);
        var weatherDto = weatherClient.getWeather("Warsaw");
        assertThat(weatherDto).isNotNull();
        assertThat(weatherDto).isEqualTo(WEATHER_DTO);
    }
}