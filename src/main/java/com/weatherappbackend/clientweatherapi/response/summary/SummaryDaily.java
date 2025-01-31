package com.weatherappbackend.clientweatherapi.response.summary;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public record SummaryDaily(
        @JsonProperty("sunshine_duration")
        double[] sunshineDurationSec,

        @JsonProperty("temperature_2m_max")
        double[] temperatureMax,

        @JsonProperty("temperature_2m_min")
        double[] temperatureMin,

        @JsonProperty("precipitation_sum")
        double[] precipitationSum
) {
}
