package com.weatherappbackend.weather.weeksummary.description;

import java.util.Arrays;

public class Wind extends DescriptionElement {

    public Wind(String id, double[] values) {
        super(id, values);
    }

    @Override
    public double count() {
        double[] values = getValues();
        return Arrays.stream(values)
                .average()
                .orElse(0);
    }

    @Override
    public boolean chooseStatus(Double avg) {
        // windy week == avg >=20 km/h
        return avg >= 20;
    }
}
