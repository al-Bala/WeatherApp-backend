package com.weatherappbackend.weather.weeksummary.description;

import java.util.Arrays;

public class Precipitation extends DescriptionElement {

    public Precipitation(String id, double[] values) {
        super(id, values);
    }

    @Override
    public double count() {
        double[] values = getValues();
        return Arrays.stream(values)
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
