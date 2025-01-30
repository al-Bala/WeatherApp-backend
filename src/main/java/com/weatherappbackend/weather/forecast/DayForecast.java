package com.weatherappbackend.weather.forecast;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record DayForecast(
        String time,
        Integer weatherCode,
        Double temperatureMax,
        Double temperatureMin,
        BigDecimal sunshineDurationSec
) {
}
