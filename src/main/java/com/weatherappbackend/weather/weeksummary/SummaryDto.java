package com.weatherappbackend.weather.weeksummary;

import lombok.Builder;

import java.util.Map;

@Builder
public record SummaryDto(
        double avgPressure,
        double avgSunTimeExposure,
        double minWeekTempC,
        double maxWeekTempC,
        Map<String, Boolean> description
) {
}
