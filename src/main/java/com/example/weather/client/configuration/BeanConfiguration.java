package com.example.weather.client.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


@Component
@RequiredArgsConstructor
public class BeanConfiguration {


    @Bean
    public RestTemplate restTemplate(ObjectMapper objectMapper) {

        return new RestTemplateBuilder()
                .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .messageConverters(new MappingJackson2HttpMessageConverter(objectMapper))
                .build();
    }

    @Autowired
    public void setNamingStrategy(ObjectMapper objectMapper){
        objectMapper.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);
    }

}
