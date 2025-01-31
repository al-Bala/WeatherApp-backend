package com.weatherappbackend.weather.weeksummary.description;

import lombok.Getter;

@Getter
public abstract class DescriptionElement {
    private final String id;
    private final double[] values;

    public DescriptionElement(String id, double[] values) {
        this.id = id;
        this.values = values;
    }

    public abstract double count();

    public abstract boolean chooseStatus(Double avg);
}
