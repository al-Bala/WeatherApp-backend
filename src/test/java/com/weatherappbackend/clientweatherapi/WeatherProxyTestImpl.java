package com.weatherappbackend.clientweatherapi;

import com.weatherappbackend.clientweatherapi.response.forecast.ForecastClientResponse;
import com.weatherappbackend.clientweatherapi.response.forecast.ForecastDaily;
import com.weatherappbackend.clientweatherapi.response.summary.SummaryClientResponse;
import com.weatherappbackend.clientweatherapi.response.summary.SummaryDaily;
import com.weatherappbackend.clientweatherapi.response.summary.SummaryHourly;

import java.util.List;

public class WeatherProxyTestImpl implements WeatherProxy{

    @Override
    public ForecastClientResponse makeQueryForForecastWeather(double latitude, double longitude, String[] daily) {
        return new ForecastClientResponse(
                ForecastDaily.builder()
                        .time(List.of())
                        .weather_code(List.of())
                        .temperature_2m_max(List.of())
                        .temperature_2m_min(List.of())
                        .sunshine_duration(List.of())
                        .build()
        );
    }

    @Override
    public SummaryClientResponse makeQueryForSummaryWeather(double latitude, double longitude, String[] hourly, String[] daily) {
        return new SummaryClientResponse(
                SummaryHourly.builder()
                        .pressure_msl(List.of(4.0, 6.0))
                        .wind_speed_10m(List.of(10.0, 20.0))
                        .build(),
                SummaryDaily.builder()
                        .sunshine_duration(List.of(400.0, 600.0))
                        .temperature_2m_max(List.of(-2.0, 6.0, 8.0))
                        .temperature_2m_min(List.of(-7.5, -1.0, 0.0))
                        .precipitation_sum(List.of(0.0, 2.0, 0.0, 3.0, 3.0, 3.0, 0.0))
                        .build()
        );
    }
}
