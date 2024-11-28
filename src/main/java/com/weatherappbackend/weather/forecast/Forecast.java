package com.weatherappbackend.weather.forecast;

import java.time.LocalDate;

public interface Forecast {
    Forecast getDay(LocalDate date);
    int countEnergy();
}
