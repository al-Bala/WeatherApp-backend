package com.weatherappbackend.weather.forecast;

import java.util.List;

public record ForecastWeather(
        List<DayForecast> forecastDays
) {
}
