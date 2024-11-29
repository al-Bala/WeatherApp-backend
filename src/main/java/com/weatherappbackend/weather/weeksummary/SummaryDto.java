package com.weatherappbackend.weather.weeksummary;

import lombok.Builder;

import java.util.Map;

@Builder
public record SummaryDto(
        Double avgPressure,
        Double avgSunTimeExposure,
        Double minWeekTempC,
        Double maxWeekTempC,
        Map<String, Boolean> description
) {
}
