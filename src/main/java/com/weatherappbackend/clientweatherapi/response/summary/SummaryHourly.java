package com.weatherappbackend.clientweatherapi.response.summary;

import lombok.Builder;

import java.util.List;

@Builder
public record SummaryHourly(
        List<Double> pressure_msl,
        List<Double> wind_speed_10m
) {
}
