package com.weatherappbackend.weather.forecast;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record ForecastDto(
        LocalDate date,
        int code,
        int minDayTempC,
        int maxDayTempC,
        double generatedEnergyKWH
) {
}
