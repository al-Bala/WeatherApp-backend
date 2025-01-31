package com.weatherappbackend.weather.weeksummary;

import com.weatherappbackend.weather.weeksummary.description.DescriptionElement;

import java.util.List;
import java.util.Map;

public interface Summary {
    // hourly:pressure_msl && daily:sunshine_duration
    double countAvg(double[] values);
    // daily:temperature_2m_max
    double chooseMax(double[] values);
    // daily:temperature_2m_min
    double chooseMin(double[] values);
    // daily:precipitation_sum && hourly:wind_speed_10m
    Map<String, Boolean> createDescription(List<DescriptionElement> descElements);
}
