package com.weatherappbackend.weatherforecast.week;

import com.weatherappbackend.weatherforecast.week.description.DescriptionElement;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface Week {
    Week getWeek(LocalDate startDate);
    double countAvg(List<Double> values);
    double chooseMax(List<Double> values);
    double chooseMin(List<Double> values);
    Map<String, Boolean> createDescription(List<DescriptionElement> descElements);
}
