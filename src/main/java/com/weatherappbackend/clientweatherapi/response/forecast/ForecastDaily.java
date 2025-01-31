package com.weatherappbackend.clientweatherapi.response.forecast;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

@Builder
public record ForecastDaily(
        @JsonProperty("time")
        List<LocalDate> date,

        @JsonProperty("weather_code")
        List<Integer> weatherCode,

        @JsonProperty("temperature_2m_max")
        List<Double> temperatureMax,

        @JsonProperty("temperature_2m_min")
        List<Double> temperatureMin,

        @JsonProperty("sunshine_duration")
        double[] sunshineDurationSec
) {
}
