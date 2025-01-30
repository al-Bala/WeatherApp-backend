package com.weatherappbackend.weather;

import com.weatherappbackend.weather.forecast.DayForecast;
import com.weatherappbackend.weather.forecast.Forecast;
import com.weatherappbackend.weather.forecast.ForecastDayDto;
import com.weatherappbackend.weather.forecast.ForecastWeather;
import com.weatherappbackend.weather.weeksummary.Summary;
import com.weatherappbackend.weather.weeksummary.SummaryDto;
import com.weatherappbackend.weather.weeksummary.SummaryWeather;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@Component
public class Weather {
    private Forecast forecast;
    private Summary summary;

    public List<ForecastDayDto> getWeatherForecast(ForecastWeather forecastWeather){
        List<ForecastDayDto> weatherForecast = new ArrayList<>();
        for(DayForecast day : forecastWeather.forecastDays()){
            ForecastDayDto forecastDayDto = ForecastDayDto.builder()
                    .date(day.time())
                    .code(day.weatherCode())
                    .minDayTempC(day.temperatureMin())
                    .maxDayTempC(day.temperatureMax())
                    .generatedPVEnergyKWH(forecast.countEnergy(day.sunshineDurationSec()))
                    .build();
            weatherForecast.add(forecastDayDto);
        }
        return weatherForecast;
    }

    public SummaryDto getWeekSummary(SummaryWeather summaryWeather){
        double avgPressure = summary.countAvg(summaryWeather.pressureMsl());
        double avgSunTimeExposure = summary.countAvg(summaryWeather.sunshineDuration());
        double minWeekTempC = summary.chooseMin(summaryWeather.temperatureMin());
        double maxWeekTempC = summary.chooseMax(summaryWeather.temperatureMax());;
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
