package com.weatherappbackend.weather.forecast;

import lombok.Builder;

@Builder
public record ForecastDayDto(
        String date,
        Integer code,
        Double minDayTempC,
        Double maxDayTempC,
        Double generatedPVEnergyKWH
) {
}
