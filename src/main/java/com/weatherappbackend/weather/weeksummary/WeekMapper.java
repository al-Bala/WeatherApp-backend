package com.weatherappbackend.weather.weeksummary;

import com.weatherappbackend.clientweatherapi.response.summary.SummaryClientResponse;
import com.weatherappbackend.weather.weeksummary.description.DescriptionElement;
import com.weatherappbackend.weather.weeksummary.description.Precipitation;
import com.weatherappbackend.weather.weeksummary.description.Wind;

import java.util.List;

public class WeekMapper {

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
