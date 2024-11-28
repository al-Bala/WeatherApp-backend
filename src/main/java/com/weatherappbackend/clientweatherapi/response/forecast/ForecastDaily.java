package com.weatherappbackend.clientweatherapi.response.forecast;

import lombok.Builder;

import java.util.List;

@Builder
public record ForecastDaily(
        List<String> time,
        List<Integer> weather_code,
        List<Double> temperature_2m_max,
        List<Double> temperature_2m_min,
        List<Double> sunshine_duration
) {
}
