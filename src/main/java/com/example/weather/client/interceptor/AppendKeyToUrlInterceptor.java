package com.example.weather.client.interceptor;

import com.example.weather.client.wrappers.HttpRequestWrapperImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
public class AppendKeyToUrlInterceptor implements ClientHttpRequestInterceptor {

    @Value("${openweather.api.key}")
    private String keyValue;

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        var uri = new HttpRequestWrapperImpl(request, keyValue).getURI();
        log.info("overriding {}", uri);
        return execution.execute(new HttpRequestWrapperImpl(request, keyValue), body);
    }
}
