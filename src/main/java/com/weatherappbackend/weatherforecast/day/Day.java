package com.weatherappbackend.weatherforecast.day;

import java.time.LocalDate;

public interface Day {
    Day getDay(LocalDate date);
    int countEnergy();
}
