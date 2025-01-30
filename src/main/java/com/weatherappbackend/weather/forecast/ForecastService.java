package com.weatherappbackend.weather.forecast;

import com.weatherappbackend.weather.Rounding;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

@Component
public class ForecastService implements Forecast, Rounding {

    private final static BigDecimal PHOTOVOLTAIC_POWER = new BigDecimal("2.5");
    private final static BigDecimal PANELS_EFFICIENCY = new BigDecimal("0.2");

    @Override
    public double countEnergy(BigDecimal sunshineDurationSec) {
        BigDecimal hoursOfSunshine = sunshineDurationSec.divide(new BigDecimal("3600"), 3, RoundingMode.HALF_UP);
        BigDecimal generatedEnergy = PHOTOVOLTAIC_POWER.multiply(hoursOfSunshine ).multiply(PANELS_EFFICIENCY);
        return generatedEnergy
                .round(new MathContext(3))
                .doubleValue();
    }
}
