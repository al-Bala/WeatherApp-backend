package com.weatherappbackend.weather.controller;

import com.weatherappbackend.weather.forecast.ForecastDayDto;
import com.weatherappbackend.weather.forecast.ForecastFacade;
import com.weatherappbackend.weather.weeksummary.SummaryDto;
import com.weatherappbackend.weather.weeksummary.SummaryFacade;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
public class WeatherController {
    private final ForecastFacade forecastFacade;
    private final SummaryFacade summaryFacade;

    @GetMapping("/weather-forecast")
    public ResponseEntity<List<ForecastDayDto>> getWeatherForecast(
            @RequestParam @Min(-90) @Max(90) Double latitude,
            @RequestParam @Min(-180) @Max(180) Double longitude
    ) {
        List<ForecastDayDto> weatherForecast = forecastFacade.getWeatherForecast(latitude, longitude);
        return ResponseEntity.ok(weatherForecast);
    }

    @GetMapping("/week-summary")
    public ResponseEntity<SummaryDto> getWeekSummary(
            @RequestParam @Min(-90) @Max(90) Double latitude,
            @RequestParam @Min(-180) @Max(180) Double longitude
    ) {
        SummaryDto summaryDto = summaryFacade.getWeekSummary(latitude, longitude);
        return ResponseEntity.ok(summaryDto);
    }
}
