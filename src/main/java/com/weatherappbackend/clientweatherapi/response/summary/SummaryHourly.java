package com.weatherappbackend.clientweatherapi.response.summary;

import java.util.List;

public record SummaryHourly(
        List<Double> pressure_msl,
        List<Double> wind_speed_10m
) {
}
