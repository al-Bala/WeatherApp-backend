package com.weatherappbackend.clientweatherapi.response.summary;

import lombok.Builder;

import java.util.List;

@Builder
public record SummaryDaily(
        List<Double> sunshine_duration,
        List<Double> temperature_2m_max,
        List<Double> temperature_2m_min,
        List<Double> precipitation_sum
) {
}
