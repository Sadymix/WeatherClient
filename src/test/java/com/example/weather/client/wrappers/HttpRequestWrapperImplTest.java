package com.example.weather.client.wrappers;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.support.HttpRequestWrapper;

import java.net.URI;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class HttpRequestWrapperImplTest {

    private final HttpRequest httpRequest = mock(HttpRequest.class);
    private final String keyValue = "value";
    private HttpRequestWrapperImpl httpRequestWrapperImpl = new HttpRequestWrapperImpl(httpRequest, keyValue);

    @SneakyThrows
    @Test
    void testGetURI() {
        HttpRequestWrapper httpRequestWrapper = mock(HttpRequestWrapper.class);
        var uri = new URI(keyValue);
        when(httpRequestWrapper.getURI()).thenReturn(uri);
        var implURI = httpRequestWrapperImpl.getURI();
        assertThat(implURI.toString()).contains(keyValue);
        assertThat(implURI.toString()).isEqualTo("null&appid=" + keyValue);
    }
}