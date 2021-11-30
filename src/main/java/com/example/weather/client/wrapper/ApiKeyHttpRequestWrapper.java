package com.example.weather.client.wrapper;

import org.springframework.http.HttpRequest;
import org.springframework.http.client.support.HttpRequestWrapper;

import java.net.URI;
import java.net.URISyntaxException;

public class ApiKeyHttpRequestWrapper extends HttpRequestWrapper {

    private final String keyValue;

    public ApiKeyHttpRequestWrapper(HttpRequest request, String keyValue) {
        super(request);
        this.keyValue = keyValue;
    }

    @Override
    public URI getURI() {
        try {
            return new URI(super.getURI() + "&appid=" + keyValue);
        } catch (URISyntaxException e) {
            e.printStackTrace();
            throw new RuntimeException("Given URI: " + super.getURI() + "&appid=" + keyValue + " is incorrect");
        }
    }
}
