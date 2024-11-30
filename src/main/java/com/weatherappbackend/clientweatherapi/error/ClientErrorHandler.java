package com.weatherappbackend.clientweatherapi.error;

import com.weatherappbackend.weather.controller.WeatherController;
import feign.FeignException;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@Log4j2
@ControllerAdvice(assignableTypes = WeatherController.class)
public class ClientErrorHandler {

    @ExceptionHandler(FeignException.class)
    @ResponseBody
    public ResponseEntity<String> handleApiError(FeignException exception) {
        if(exception instanceof FeignException.FeignClientException){
            log.error("Client exception: {}", exception.status());
        }
        else if(exception instanceof FeignException.FeignServerException){
            log.error("Server exception: {}", exception.status());
        }
        else {
            log.error("Feign exception: {} {}", exception.getMessage(), exception.status());
        }
        return ResponseEntity.status(HttpStatus.valueOf(exception.status())).body(exception.getMessage());
    }

}
