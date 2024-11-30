package com.weatherappbackend.weather.controller;

import com.weatherappbackend.clientweatherapi.ClientService;
import com.weatherappbackend.clientweatherapi.response.forecast.ForecastClientResponse;
import com.weatherappbackend.clientweatherapi.response.summary.SummaryClientResponse;
import com.weatherappbackend.weather.Weather;
import com.weatherappbackend.weather.WeatherMapper;
import com.weatherappbackend.weather.forecast.ForecastDayDto;
import com.weatherappbackend.weather.forecast.ForecastWeather;
import com.weatherappbackend.weather.weeksummary.SummaryDto;
import com.weatherappbackend.weather.weeksummary.SummaryWeather;
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
            @PathVariable String latitude,
            @PathVariable String longitude
    ) {
        if(isCoordinatesValid(latitude, longitude)) {
            return ResponseEntity.badRequest().build();
        }
        ForecastClientResponse forecastClientResponse =
                clientService.findForecastWeather(Double.parseDouble(latitude), Double.parseDouble(longitude));
        ForecastWeather forecastWeather = WeatherMapper.mapFromResponseToForecastWeather(forecastClientResponse);
        List<ForecastDayDto> weatherForecast = weather.getWeatherForecast(forecastWeather);
        return ResponseEntity.ok(weatherForecast);
    }

    @GetMapping("/week-summary/{latitude}/{longitude}")
    public ResponseEntity<SummaryDto> getWeekSummary(
            @PathVariable String latitude,
            @PathVariable String longitude
    ) {
        if(isCoordinatesValid(latitude, longitude)) {
            return ResponseEntity.badRequest().build();
        }
        SummaryClientResponse summaryClientResponse =
                clientService.getSummaryWeather(Double.parseDouble(latitude), Double.parseDouble(longitude));
        SummaryWeather summaryWeather = WeatherMapper.mapFromResponseToSummaryWeather(summaryClientResponse);
        SummaryDto summaryDto = weather.getWeekSummary(summaryWeather);
        return ResponseEntity.ok(summaryDto);
    }

    private static boolean isCoordinatesValid(String latitude, String longitude) {
        String regex = "\\d+.\\d+";
        return !latitude.matches(regex) && !longitude.matches(regex);
    }
}
