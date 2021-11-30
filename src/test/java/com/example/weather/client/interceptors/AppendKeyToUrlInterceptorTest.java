package com.example.weather.client.interceptors;

import com.example.weather.client.wrapper.HttpRequestWrapperImpl;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.client.ClientHttpRequestExecution;

import java.net.URI;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AppendKeyToUrlInterceptorTest {

    @Mock
    private ClientHttpRequestExecution clientHttpRequestExecution;
    @InjectMocks
    private AppendKeyToUrlInterceptor appendKeyToUrlInterceptor;

    @SneakyThrows
    @Test
    void testIntercept() {
        var httpRequestWrapper = mock(HttpRequestWrapperImpl.class);
        when(httpRequestWrapper.getURI()).thenReturn(new URI("asdfg"));
        appendKeyToUrlInterceptor.intercept(httpRequestWrapper, null, clientHttpRequestExecution);
        assertTrue(httpRequestWrapper.getURI().toString().contains("asdfg"));
    }
}
