package com.weatherappbackend.weatherforecast.controller;

import com.weatherappbackend.weatherforecast.day.DayDto;
import com.weatherappbackend.weatherforecast.week.WeekDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class WeatherController {

    @GetMapping()
    public ResponseEntity<List<DayDto>> getWeatherFor7Days() {
        return null;
    }

    @GetMapping()
    public ResponseEntity<WeekDto> getWeekSummary() {
        return null;
    }

}
