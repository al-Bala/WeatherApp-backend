package com.weatherappbackend.weatherforecast;

import com.weatherappbackend.weatherforecast.day.Day;
import com.weatherappbackend.weatherforecast.week.Week;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class WForecast {
    private Day day;
    private Week week;

    public Day getDayWeather(LocalDate date){
        return day.getDay(date);
    }

    public Week getWeekSummary(LocalDate startDate){
        return week.getWeek(startDate);
    }
}
