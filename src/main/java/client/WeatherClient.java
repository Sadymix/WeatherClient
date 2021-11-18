package client;

import dto.WeatherDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class WeatherClient {

    private final RestTemplate restTemplate;
    @Value("${app.base.url}")
    private String url;
    @Value("${openweather.api.key}")
    private String keyValue;

    public WeatherDto getWeather(String city) {
        return restTemplate.getForObject(url + "?q=" + city + "&appud="+keyValue,
                WeatherDto.class);
    }

    public void deleteWeather(String city){
        restTemplate.delete(url + "?q=" + city + "&appud="+keyValue);
    }
}
