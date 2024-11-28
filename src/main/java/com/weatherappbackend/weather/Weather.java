package com.weatherappbackend.weather;

import com.weatherappbackend.weather.forecast.Forecast;
import com.weatherappbackend.weather.forecast.ForecastDto;
import com.weatherappbackend.weather.weeksummary.SummaryWeather;
import com.weatherappbackend.weather.weeksummary.Summary;
import com.weatherappbackend.weather.weeksummary.SummaryDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Map;

@AllArgsConstructor
@Component
public class Weather {
    private Forecast forecast;
    private Summary summary;

    public ForecastDto getWeatherForecast(LocalDate date){
        return ForecastDto.builder().build();
    }

    public SummaryDto getWeekSummary(SummaryWeather summaryWeather){
        double avgPressure = summary.countAvg(summaryWeather.pressureMsl());
        double avgSunTimeExposure = summary.countAvg(summaryWeather.sunshineDuration());
        double minWeekTempC = summary.chooseMax(summaryWeather.temperatureMax());
        double maxWeekTempC = summary.chooseMin(summaryWeather.temperatureMin());;
        Map<String, Boolean> description = summary.createDescription(summaryWeather.descElements());

        return SummaryDto.builder()
                .avgPressure(avgPressure)
                .avgSunTimeExposure(avgSunTimeExposure)
                .minWeekTempC(minWeekTempC)
                .maxWeekTempC(maxWeekTempC)
                .description(description)
                .build();
    }
}
