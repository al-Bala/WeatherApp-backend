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
                        .date(getNextSevenDays())
                        .weatherCode(List.of(1,2,3,4,5,6,7,8,9))
                        .temperatureMax(List.of(1.0,2.0,3.0,4.0,5.0,6.0,7.0))
                        .temperatureMin(List.of(1.0,2.0,3.0,4.0,5.0,6.0,7.0))
                        .sunshineDurationSec(new double[]{36000.00, 16000.00, 4000.00, 10000.00, 6000.00, 0.00, 0.00})
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
                        .pressureMsl(new double[]{4.0, 6.0})
                        .windSpeed(new double[]{10.0, 20.0})
                        .build(),
                SummaryDaily.builder()
                        .sunshineDurationSec(new double[]{400.0, 600.0})
                        .temperatureMax(new double[]{-2.0, 6.0, 8.0})
                        .temperatureMin(new double[]{-7.5, -1.0, 0.0})
                        .precipitationSum(new double[]{0.0, 2.0, 0.0, 3.0, 3.0, 3.0, 0.0})
                        .build()
        );
    }
}
