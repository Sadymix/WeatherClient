package com.example.weather.client.interceptors;

import com.example.weather.client.wrapper.ApiKeyHttpRequestWrapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;

import java.net.URI;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ApiKeyAppendingInterceptorTest {

    @Mock
    private ClientHttpRequestExecution clientHttpRequestExecution;
    @Mock
    private HttpRequest httpRequest;
    @InjectMocks
    private ApiKeyAppendingInterceptor apiKeyAppendingInterceptor;
    @Captor
    private ArgumentCaptor<HttpRequest> argumentCaptor;

    @SneakyThrows
    @Test
    void testIntercept(@Mock ApiKeyHttpRequestWrapper httpRequestWrapper) {
        when(httpRequestWrapper.getURI()).thenReturn(new URI("asdfg"));
        apiKeyAppendingInterceptor.intercept(argumentCaptor.capture(), null, clientHttpRequestExecution);
        assertThat(argumentCaptor.getValue().getURI()).isEqualTo(httpRequestWrapper.getURI());
    }
}
