package com.weatherappbackend.weatherforecast.day;

import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DayWeather implements Day {
    private LocalDate date;
    private int code;
    private int minDayTemp;
    private int maxDayTemp;
    private double generatedEnergyKWH;

    @Override
    public Day getDay(LocalDate date) {
        return new DayWeather();
    }

    @Override
    public int countEnergy() {
        return 0;
    }

}
