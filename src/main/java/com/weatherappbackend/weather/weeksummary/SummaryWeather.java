package com.weatherappbackend.weather.weeksummary;

import com.weatherappbackend.weather.weeksummary.description.DescriptionElement;
import lombok.Builder;

import java.util.List;

@Builder
public record SummaryWeather(
        List<Double> pressureMsl,
        List<Double> sunshineDuration,
        List<Double> temperatureMax,
        List<Double> temperatureMin,
        List<DescriptionElement> descElements
) {
}
