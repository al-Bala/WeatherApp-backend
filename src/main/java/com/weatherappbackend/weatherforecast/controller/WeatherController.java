package com.weatherappbackend.weatherforecast.controller;

import com.weatherappbackend.clientweatherapi.ClientService;
import com.weatherappbackend.clientweatherapi.response.ClientResponse;
import com.weatherappbackend.weatherforecast.day.DayDto;
import com.weatherappbackend.weatherforecast.week.WeekDto;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
public class WeatherController {
    private final ClientService clientService;

    @GetMapping("/weather-forecast")
    public ResponseEntity<List<DayDto>> getWeatherForecast(double latitude, double longitude) {
        ClientResponse weatherFor7Days = clientService.getWeatherFor7Days(latitude, longitude);
        return null;
    }

    @GetMapping("/week-summary")
    public ResponseEntity<WeekDto> getWeekSummary() {
        return null;
    }

}
