package com.weatherappbackend.weather.weeksummary.description;

import java.util.List;

public class Precipitation extends DescriptionElement {

    public Precipitation(String id, List<Double> values) {
        super(id, values);
    }

    @Override
    public double count() {
        List<Double> values = getValues();
        return values.stream()
                .filter(v -> v > 1.0)
                .count();
    }

    @Override
    public boolean chooseStatus(Double rainyDaysNr) {
        // rainy day == 1mm
        // rainy week = 4 days with >1mm
        return rainyDaysNr >= 4;
    }
}
