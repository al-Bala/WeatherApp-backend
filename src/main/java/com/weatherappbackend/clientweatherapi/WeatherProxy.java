package com.weatherappbackend.clientweatherapi;

import com.weatherappbackend.clientweatherapi.response.forecast.ForecastClientResponse;
import com.weatherappbackend.clientweatherapi.response.summary.SummaryClientResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "weather-client")
@RequestMapping("/v1/forecast")
public interface WeatherProxy {

    @GetMapping()
    ForecastClientResponse makeQueryForForecastWeather(
            @RequestParam("latitude") double latitude,
            @RequestParam("longitude") double longitude,
            @RequestParam("daily") String[] daily
    );

    @GetMapping()
    SummaryClientResponse makeQueryForSummaryWeather(
            @RequestParam("latitude") double latitude,
            @RequestParam("longitude") double longitude,
            @RequestParam("hourly") String[] hourly,
            @RequestParam("daily") String[] daily);
}
