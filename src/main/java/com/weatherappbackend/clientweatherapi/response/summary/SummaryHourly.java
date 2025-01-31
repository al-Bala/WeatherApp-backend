package com.weatherappbackend.clientweatherapi.response.summary;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public record SummaryHourly(
        @JsonProperty("pressure_msl")
        double[] pressureMsl,

        @JsonProperty("wind_speed_10m")
        double[] windSpeed
) {
}
