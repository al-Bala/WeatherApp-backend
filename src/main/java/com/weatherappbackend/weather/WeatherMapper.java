package com.weatherappbackend.weather;

import com.weatherappbackend.clientweatherapi.response.forecast.ForecastClientResponse;
import com.weatherappbackend.clientweatherapi.response.forecast.ForecastDaily;
import com.weatherappbackend.clientweatherapi.response.summary.SummaryClientResponse;
import com.weatherappbackend.weather.forecast.DayForecast;
import com.weatherappbackend.weather.forecast.ForecastWeather;
import com.weatherappbackend.weather.weeksummary.SummaryWeather;
import com.weatherappbackend.weather.weeksummary.description.DescriptionElement;
import com.weatherappbackend.weather.weeksummary.description.Precipitation;
import com.weatherappbackend.weather.weeksummary.description.Wind;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class WeatherMapper {

    public static ForecastWeather mapFromResponseToForecastWeather(ForecastClientResponse forecastClientResponse) {
        int daysNr = forecastClientResponse.daily().time().size();
        List<DayForecast> forecastDays = new ArrayList<>();

        for(int i=0; i<daysNr; i++) {
            ForecastDaily response = forecastClientResponse.daily();
            DayForecast dayForecast = DayForecast.builder()
                    .time(formatDate(response.time().get(i)))
                    .weatherCode(response.weather_code().get(i))
                    .temperatureMax(response.temperature_2m_max().get(i))
                    .temperatureMin(response.temperature_2m_min().get(i))
                    .sunshineDurationSec(BigDecimal.valueOf(response.sunshine_duration().get(i)))
                    .build();
            forecastDays.add(dayForecast);
        }
        return new ForecastWeather(forecastDays);
    }

    private static String formatDate(LocalDate date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        return formatter.format(date);
    }

    public static SummaryWeather mapFromResponseToSummaryWeather(SummaryClientResponse summaryClientResponse) {
        return SummaryWeather.builder()
                .pressureMsl(summaryClientResponse.hourly().pressure_msl())
                .sunshineDuration(summaryClientResponse.daily().sunshine_duration())
                .temperatureMin(summaryClientResponse.daily().temperature_2m_min())
                .temperatureMax(summaryClientResponse.daily().temperature_2m_max())
                .descElements(mapToDescElements(summaryClientResponse))
                .build();
    }

    private static List<DescriptionElement> mapToDescElements(SummaryClientResponse summaryClientResponse) {
        List<Double> precipitationSum = summaryClientResponse.daily().precipitation_sum();
        List<Double> windSpeed = summaryClientResponse.hourly().wind_speed_10m();

        return List.of(
                new Precipitation("precipitation", precipitationSum),
                new Wind("wind", windSpeed)
        );
    }
}
