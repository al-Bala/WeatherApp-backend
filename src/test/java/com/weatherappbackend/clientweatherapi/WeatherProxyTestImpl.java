package com.weatherappbackend.clientweatherapi;

import com.weatherappbackend.clientweatherapi.response.forecast.ForecastClientResponse;
import com.weatherappbackend.clientweatherapi.response.forecast.ForecastDaily;
import com.weatherappbackend.clientweatherapi.response.summary.SummaryClientResponse;
import com.weatherappbackend.clientweatherapi.response.summary.SummaryDaily;
import com.weatherappbackend.clientweatherapi.response.summary.SummaryHourly;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.IntStream;

public class WeatherProxyTestImpl implements WeatherProxy{

    @Override
    public ForecastClientResponse makeQueryForForecastWeather(double latitude, double longitude, String[] daily) {
        return new ForecastClientResponse(
                ForecastDaily.builder()
                        .time(getNextSevenDays())
                        .weather_code(List.of(1,2,3,4,5,6,7,8,9))
                        .temperature_2m_max(List.of(1.0,2.0,3.0,4.0,5.0,6.0,7.0))
                        .temperature_2m_min(List.of(1.0,2.0,3.0,4.0,5.0,6.0,7.0))
                        .sunshine_duration(List.of(36000.00, 16000.00, 4000.00, 10000.00, 6000.00, 0.00, 0.00))
                        .build()
        );
    }

    private static List<LocalDate> getNextSevenDays() {
        LocalDate today = LocalDate.of(2024, 11, 1);
        return IntStream.range(0, 7)
                .mapToObj(today::plusDays)
                .toList();
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
