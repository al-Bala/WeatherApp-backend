package com.weatherappbackend.clientweatherapi.response.forecast;

import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

@Builder
public record ForecastDaily(
        List<LocalDate> time,
        List<Integer> weather_code,
        List<Double> temperature_2m_max,
        List<Double> temperature_2m_min,
        List<Double> sunshine_duration
) {
}
