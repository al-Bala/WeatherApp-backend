package com.weatherappbackend.weatherforecast.week;

import com.weatherappbackend.weatherforecast.day.DayWeather;
import com.weatherappbackend.weatherforecast.week.description.DescriptionElement;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WeekSummary extends DayWeather implements Week {
    private double avgPressure;
    private double avgSunTimeExposure;
    private double minWeekTempC;
    private double maxWeekTempC;
    private Map<String, Boolean> description;

    @Override
    public Week getWeek(LocalDate startDate) {
        return new WeekSummary();
    }

    @Override
    public double countAvg(List<Double> values) {
        double sum = values.stream()
                .mapToDouble(Double::doubleValue)
                .sum();
        return sum / values.size();
    }

    @Override
    public double chooseMax(List<Double> values) {
        return values.stream()
                .max(Double::compareTo)
                .orElse(0.0001);
    }

    @Override
    public double chooseMin(List<Double> values) {
        return values.stream()
                .min(Double::compareTo)
                .orElse(0.0001);
    }

    @Override
    public Map<String, Boolean> createDescription(List<DescriptionElement> descElements) {
        Map<String, Boolean> description = new HashMap<>();

        for(DescriptionElement descElement : descElements) {
            if(!descElement.getValues().isEmpty())
                addToDescription(descElement, description);
        }
        if(description.isEmpty())
            description.put("default", true);
        return description;
    }

    private void addToDescription(DescriptionElement descElement, Map<String, Boolean> description) {
        double sum = descElement.sumValues();
        boolean status = descElement.chooseStatus(sum);
        description.put(descElement.getId(), status);
    }
}
