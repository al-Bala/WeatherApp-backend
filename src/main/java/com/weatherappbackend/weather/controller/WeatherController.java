package com.weatherappbackend.weather.controller;

import com.weatherappbackend.clientweatherapi.ClientService;
import com.weatherappbackend.clientweatherapi.response.forecast.ForecastClientResponse;
import com.weatherappbackend.clientweatherapi.response.summary.SummaryClientResponse;
import com.weatherappbackend.weather.Weather;
import com.weatherappbackend.weather.forecast.ForecastDayDto;
import com.weatherappbackend.weather.forecast.ForecastWeather;
import com.weatherappbackend.weather.weeksummary.SummaryWeather;
import com.weatherappbackend.weather.weeksummary.SummaryDto;
import com.weatherappbackend.weather.WeatherMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
public class WeatherController {
    private final ClientService clientService;
    private final Weather weather;

    @GetMapping("/weather-forecast/{latitude}/{longitude}")
    public ResponseEntity<List<ForecastDayDto>> getWeatherForecast(
            @PathVariable double latitude,
            @PathVariable double longitude
    ) {
        ForecastClientResponse forecastClientResponse = clientService.findForecastWeather(latitude, longitude);
        ForecastWeather forecastWeather = WeatherMapper.mapFromResponseToForecastWeather(forecastClientResponse);
        List<ForecastDayDto> weatherForecast = weather.getWeatherForecast(forecastWeather);
        return ResponseEntity.ok(weatherForecast);
    }

    @GetMapping("/week-summary/{latitude}/{longitude}")
    public ResponseEntity<SummaryDto> getWeekSummary(
            @PathVariable double latitude,
            @PathVariable double longitude
    ) {
        SummaryClientResponse summaryClientResponse = clientService.getSummaryWeather(latitude, longitude);
        SummaryWeather summaryWeather = WeatherMapper.mapFromResponseToSummaryWeather(summaryClientResponse);
        SummaryDto summaryDto = weather.getWeekSummary(summaryWeather);
        return ResponseEntity.ok(summaryDto);
    }

}
