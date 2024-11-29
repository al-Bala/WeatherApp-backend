package com.weatherappbackend.weather.forecast;

import lombok.Builder;

@Builder
public record DayForecast(
        String time,
        Integer weatherCode,
        Double temperatureMax,
        Double temperatureMin,
        Double sunshineDuration
) {
}
