package com.weatherappbackend.clientweatherapi.response;

import java.util.List;

public record Daily(
        List<String> time,
        List<Integer> weather_code,
        List<Double> temperature_2m_max,
        List<Double> temperature_2m_min,
        List<Double> sunshine_duration
) {
}
