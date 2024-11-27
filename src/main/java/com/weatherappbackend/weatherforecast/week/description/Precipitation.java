package com.weatherappbackend.weatherforecast.week.description;

import java.util.List;

public class Precipitation extends DescriptionElement {

    public Precipitation(String id, List<Double> values) {
        super(id, values);
    }

    @Override
    public boolean chooseStatus(Double value) {
        // rainy day --> 5 mm
        // 4 days * 5 mm = 20 mm
        return value >= 20;
    }
}
