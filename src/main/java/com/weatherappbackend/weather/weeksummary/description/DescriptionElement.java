package com.weatherappbackend.weather.weeksummary.description;

import com.weatherappbackend.weather.Avg;
import lombok.Getter;

import java.util.List;

@Getter
public abstract class DescriptionElement implements Avg {
    private final String id;
    private final List<Double> values;

    public DescriptionElement(String id, List<Double> values) {
        this.id = id;
        this.values = values;
    }

    public abstract double count();

    public abstract boolean chooseStatus(Double avg);
}
