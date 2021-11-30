package com.example.weather.client.interceptors;

import com.example.weather.client.wrapper.ApiKeyHttpRequestWrapper;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

@Component
public class ApiKeyAppendingInterceptor implements ClientHttpRequestInterceptor {

    @Value("${openweather.api.key}")
    private String keyValue;

    @SneakyThrows
    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) {
        return execution.execute(new ApiKeyHttpRequestWrapper(request, keyValue), body);
    }
}
