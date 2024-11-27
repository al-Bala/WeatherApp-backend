package com.weatherappbackend.clientweatherapi;

import com.weatherappbackend.clientweatherapi.response.ClientResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "weather-client")
public interface WeatherProxy {

    @GetMapping("/v1/forecast")
    ClientResponse makeQueryFor7DaysWeatherForecast(
            @RequestParam("latitude") double latitude,
            @RequestParam("longitude") double longitude,
            @RequestParam("daily") String[] daily);
}
