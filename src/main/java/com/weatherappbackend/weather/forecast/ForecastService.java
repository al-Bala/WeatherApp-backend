package com.weatherappbackend.weather.forecast;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class ForecastService implements Forecast {

    private final static BigDecimal PHOTOVOLTAIC_POWER = new BigDecimal("2.5");
    private final static BigDecimal PANELS_EFFICIENCY = new BigDecimal("0.2");

    @Override
    public double countEnergy(BigDecimal sunshineDurationSec) {
        BigDecimal hoursOfSunshine = sunshineDurationSec.divide(new BigDecimal("3600"), 3, RoundingMode.HALF_UP);
        BigDecimal generatedEnergy = PHOTOVOLTAIC_POWER.multiply(hoursOfSunshine ).multiply(PANELS_EFFICIENCY);
        return generatedEnergy
                .setScale(2, RoundingMode.HALF_UP)
                .doubleValue();
    }
}
