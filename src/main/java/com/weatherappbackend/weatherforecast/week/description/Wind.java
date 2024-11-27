package com.weatherappbackend.weatherforecast.week.description;

import java.util.List;

public class Wind extends DescriptionElement {

    public Wind(String id, List<Double> values) {
        super(id, values);
    }

    @Override
    public boolean chooseStatus(Double value) {
        // windy day --> avg 20 km/h
        // 4 days * 20 km/h = 80 km/h
        return value >= 80;
    }
}
