package com.weatherappbackend.weatherforecast.week;

import com.weatherappbackend.weatherforecast.day.DayWeather;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class WeekSummary extends DayWeather implements Week {
    private double avgPressure;
    private double avgSunTimeExposure;
    private int minWeekTempC;
    private int maxWeekTempC;
    private String summary;

    @Override
    public Week getWeek(LocalDate startDate) {
        return new WeekSummary();
    }

    @Override
    public int countAvg() {
        return 0;
    }

    @Override
    public int chooseTempExtremes() {
        return 0;
    }

    @Override
    public String generateSummary() {
        return "";
    }
}
