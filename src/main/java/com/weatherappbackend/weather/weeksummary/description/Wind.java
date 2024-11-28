package com.weatherappbackend.weather.weeksummary.description;

import java.util.List;

public class Wind extends DescriptionElement {

    public Wind(String id, List<Double> values) {
        super(id, values);
    }

    @Override
    public double count() {
        List<Double> values = getValues();
        return getAvg(values);
    }

    @Override
    public boolean chooseStatus(Double avg) {
        // windy week == avg >=20 km/h
        return avg >= 20;
    }
}
