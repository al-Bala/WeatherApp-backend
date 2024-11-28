package com.weatherappbackend.weather.forecast;

import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class ForecastService implements Forecast {
    private LocalDate date;
    private int code;
    private int minDayTemp;
    private int maxDayTemp;
    private double generatedEnergyKWH;

    @Override
    public Forecast getDay(LocalDate date) {
        return new ForecastService();
    }

    @Override
    public int countEnergy() {
        return 0;
    }

}
