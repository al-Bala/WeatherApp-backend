package com.weatherappbackend.weather.forecast;

import java.math.BigDecimal;

public interface Forecast {
    double countEnergy(BigDecimal sunshineDurationH);
}
