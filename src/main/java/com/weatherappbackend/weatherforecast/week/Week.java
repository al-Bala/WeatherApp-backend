package com.weatherappbackend.weatherforecast.week;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface Week {
    Week getWeek(LocalDate startDate);
    double countAvg(List<Double> values);
    double chooseMax(List<Double> values);
    double chooseMin(List<Double> values);
    Map<String, Boolean> generateSummary(Map<String, List<Double>> values);
}
