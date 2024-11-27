package com.weatherappbackend.weatherforecast.week.description;

import lombok.Getter;

import java.util.List;

@Getter
public abstract class DescriptionElement {
    private final String id;
    private final List<Double> values;

    public DescriptionElement(String id, List<Double> values) {
        this.id = id;
        this.values = values;
    }

    public abstract boolean chooseStatus(Double value);

    public double sumValues(){
        return values.stream()
                .mapToDouble(Double::doubleValue)
                .sum();
    }
}
