package com.weatherappbackend.weatherforecast.week;

public record WeekDto(
        double avgPressure,
        double avgSunTimeExposure,
        int minTempC,
        int maxTempC,
        String summary
) {
}
