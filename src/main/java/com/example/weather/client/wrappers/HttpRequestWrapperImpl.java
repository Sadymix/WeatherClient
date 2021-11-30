package com.example.weather.client.wrappers;

import lombok.SneakyThrows;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.support.HttpRequestWrapper;

import java.net.URI;

public class HttpRequestWrapperImpl extends HttpRequestWrapper {

    private final String keyValue;

    public HttpRequestWrapperImpl(HttpRequest request, String keyValue) {
        super(request);
        this.keyValue = keyValue;
    }

    @SneakyThrows
    @Override
    public URI getURI() {
        return new URI(super.getURI() + "&appid=" + keyValue);
    }
}
