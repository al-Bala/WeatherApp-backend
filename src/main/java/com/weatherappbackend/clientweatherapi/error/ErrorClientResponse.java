package com.weatherappbackend.clientweatherapi.error;

import org.springframework.http.HttpStatus;

public record ErrorClientResponse(
        String message,
        HttpStatus httpStatus
) {
}
