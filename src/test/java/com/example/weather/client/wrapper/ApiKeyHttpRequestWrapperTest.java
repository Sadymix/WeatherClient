package com.example.weather.client.wrapper;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpRequest;

import java.net.URI;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ApiKeyHttpRequestWrapperTest {

    private final HttpRequest httpRequest = mock(HttpRequest.class);
    private final String keyValue = "value";
    private final ApiKeyHttpRequestWrapper apiKeyHttpRequestWrapper = new ApiKeyHttpRequestWrapper(httpRequest, keyValue);

    @SneakyThrows
    @Test
    void testGetURI() {
        when(httpRequest.getURI()).thenReturn(new URI("http://api.example.com?key=value"));
        var implURI = apiKeyHttpRequestWrapper.getURI();
        assertThat(implURI).isNotNull();
        assertThat(implURI.toString()).endsWith("&appid=" + keyValue);
    }
}