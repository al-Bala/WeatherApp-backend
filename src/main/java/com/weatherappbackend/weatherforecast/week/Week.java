package com.weatherappbackend.weatherforecast.week;

import java.time.LocalDate;

public interface Week {
    Week getWeek(LocalDate startDate);
    int countAvg();
    int chooseTempExtremes();
    String generateSummary();
}
