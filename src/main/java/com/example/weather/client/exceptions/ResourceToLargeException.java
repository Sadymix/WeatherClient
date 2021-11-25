package com.example.weather.client.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "resource is to large")
public class ResourceToLargeException extends RuntimeException {

    public ResourceToLargeException(String message) {
        super(message);
    }
}
