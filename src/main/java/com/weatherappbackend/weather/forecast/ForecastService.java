package com.weatherappbackend.weather.forecast;

import com.weatherappbackend.weather.Rounding;
import org.springframework.stereotype.Component;

@Component
public class ForecastService implements Forecast, Rounding {

    private final static double PHOTOVOLTAIC_POWER = 2.5;
    private final static double PANELS_EFFICIENCY = 0.2;

    @Override
    public Double countEnergy(Double sunTimeExposure) {
        double generatedEnergy = PHOTOVOLTAIC_POWER * sunTimeExposure * PANELS_EFFICIENCY;
        return round(generatedEnergy);
    }
}
