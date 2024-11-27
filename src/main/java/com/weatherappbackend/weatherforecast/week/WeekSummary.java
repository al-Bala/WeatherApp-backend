package com.weatherappbackend.weatherforecast.week;

import com.weatherappbackend.weatherforecast.day.DayWeather;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Component
public class WeekSummary extends DayWeather implements Week {
    private double avgPressure;
    private double avgSunTimeExposure;
    private double minWeekTempC;
    private double maxWeekTempC;
    private Map<String, Boolean> summary;

    @Override
    public Week getWeek(LocalDate startDate) {
        return new WeekSummary();
    }

    @Override
    public double countAvg(List<Double> values) {
        return 0;
    }

    @Override
    public double chooseMax(List<Double> values) {
        return 0;
    }

    @Override
    public double chooseMin(List<Double> values) {
        return 0;
    }

    @Override
    public Map<String, Boolean> generateSummary(Map<String, List<Double>> values) {
        return null;
    }

}
