package com.weatherappbackend.weatherforecast.day;

import java.time.LocalDate;

public record DayDto(
        LocalDate date,
        int code,
        int minDayTempC,
        int maxDayTempC,
        double generatedEnergyKWH
) {
}
