package com.weatherappbackend.clientweatherapi.error;

import com.weatherappbackend.clientweatherapi.ClientService;
import feign.FeignException;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@Log4j2
@ControllerAdvice(assignableTypes = ClientService.class)
public class ClientErrorHandler {

    @ExceptionHandler(FeignException.class)
    @ResponseBody
    public ErrorClientResponse handleApiError(FeignException exception) {
        if(exception.getCause() instanceof FeignException.FeignClientException){
            log.error("Client exception: {}", exception.status());
        }
        if(exception.getCause() instanceof FeignException.FeignServerException){
            log.error("Server exception: {}", exception.status());
        }
        if(exception.getCause() instanceof FeignException){
            log.error("Feign exception: {} {}", exception.getMessage(), exception.status());
        }
        return new ErrorClientResponse(exception.getMessage(), HttpStatus.valueOf(exception.status()));
    }

}
