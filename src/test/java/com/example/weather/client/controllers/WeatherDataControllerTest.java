package com.example.weather.client.controllers;

import com.example.weather.client.models.dto.WeatherDataDto;
import com.example.weather.client.services.WeatherDataService;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

import static com.example.weather.client.utility.PodamUtility.makePojo;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class WeatherDataControllerTest {

    private WeatherDataDto weatherDataDto = makePojo(WeatherDataDto.class);
    private String url = "http://localhost:8080/weather/data";
    @Autowired
    private WebApplicationContext wac;
    @MockBean
    private WeatherDataService weatherDataService;
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(wac)
                .build();
    }

    @SneakyThrows
    @Test
    void testGetWeatherDataByCity() {
        final Page<WeatherDataDto> page = new PageImpl<>(List.of(weatherDataDto));
        given(weatherDataService.getWeatherDataByCity("Warsaw", 0, 10))
                .willReturn(page);
        performForGetWeatherDataByCity();
        verify(weatherDataService).getWeatherDataByCity("Warsaw", 0, 10);
    }

    @SneakyThrows
    @Test
    void testDeleteWeatherDataInTimePeriod() {
        var fromTime = LocalDateTime.of(2021, Month.SEPTEMBER, 9, 12, 12, 12);
        var toTime = LocalDateTime.of(2021, Month.NOVEMBER, 23, 15, 26, 52);
        doNothing().when(weatherDataService)
                .deleteWeatherDataInTimePeriod(fromTime, toTime);
        mockMvc.perform(delete(url + "?fromTime=2021-09-09T12:12:12&toTime=2021-11-23T15:26:52"))
                .andExpect(status().isOk());
        verify(weatherDataService).deleteWeatherDataInTimePeriod(fromTime, toTime);
    }

    @SneakyThrows
    private void performForGetWeatherDataByCity() {
        mockMvc.perform(get(url + "?city=Warsaw&page=0&size=10"))
                .andExpectAll(
                        status().isOk(),
                        jsonPath("$.content[0].coord.lon")
                                .value(weatherDataDto.getCoord().getLon()),
                        jsonPath("$.content[0].coord.lat")
                                .value(weatherDataDto.getCoord().getLat()),
                        jsonPath("$.content[0].weather[0].main",
                                equalTo(weatherDataDto.getWeather().get(0).getMain())),
                        jsonPath("$.content[0].weather[0].description",
                                equalTo(weatherDataDto.getWeather().get(0).getDescription())),
                        jsonPath("$.content[0].main.temp")
                                .value(weatherDataDto.getMain().getTemp()),
                        jsonPath("$.content[0].main.temp_max")
                                .value(weatherDataDto.getMain().getTempMax()),
                        jsonPath("$.content[0].main.temp_min")
                                .value((weatherDataDto.getMain().getTempMin())),
                        jsonPath("$.content[0].main.pressure",
                                equalTo(weatherDataDto.getMain().getPressure())),
                        jsonPath("$.content[0].main.humidity",
                                equalTo(weatherDataDto.getMain().getHumidity())),
                        jsonPath("$.content[0].wind.speed")
                                .value(weatherDataDto.getWind().getSpeed()),
                        jsonPath("$.content[0].wind.deg")
                                .value(weatherDataDto.getWind().getDeg()),
                        jsonPath("$.content[0].dt")
                                .value(weatherDataDto.getUnixTime()),
                        jsonPath("$.content[0].name")
                                .value(weatherDataDto.getName())
                );
    }
}