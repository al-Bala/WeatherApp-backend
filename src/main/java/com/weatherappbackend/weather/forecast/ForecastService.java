package com.weatherappbackend.weather.forecast;

import org.springframework.stereotype.Component;

@Component
public class ForecastService implements Forecast {

    private final static double PHOTOVOLTAIC_POWER = 2.5;
    private final static double PANELS_EFFICIENCY = 0.2;

    @Override
    public Double countEnergy(Double sunTimeExposure) {
        return PHOTOVOLTAIC_POWER * sunTimeExposure * PANELS_EFFICIENCY;
    }
}
