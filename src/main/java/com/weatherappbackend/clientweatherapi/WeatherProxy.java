package com.weatherappbackend.clientweatherapi;

import com.weatherappbackend.clientweatherapi.response.forecast.ForecastClientResponse;
import com.weatherappbackend.clientweatherapi.response.summary.SummaryClientResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "weatherClient", url = "${weather-client.url}")
public interface WeatherProxy {

    @GetMapping("/v1/forecast")
    ForecastClientResponse makeQueryForForecastWeather(
            @RequestParam("latitude") double latitude,
            @RequestParam("longitude") double longitude,
            @RequestParam("daily") String[] daily
    );

    @GetMapping("/v1/forecast")
    SummaryClientResponse makeQueryForSummaryWeather(
            @RequestParam("latitude") double latitude,
            @RequestParam("longitude") double longitude,
            @RequestParam("hourly") String[] hourly,
            @RequestParam("daily") String[] daily);
}
