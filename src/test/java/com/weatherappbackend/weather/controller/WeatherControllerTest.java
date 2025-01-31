package com.weatherappbackend.weather.controller;

import com.weatherappbackend.clientweatherapi.ClientService;
import com.weatherappbackend.clientweatherapi.WeatherProxyTestImpl;
import com.weatherappbackend.weather.forecast.Forecast;
import com.weatherappbackend.weather.forecast.ForecastDayDto;
import com.weatherappbackend.weather.forecast.ForecastFacade;
import com.weatherappbackend.weather.forecast.ForecastService;
import com.weatherappbackend.weather.weeksummary.Summary;
import com.weatherappbackend.weather.weeksummary.SummaryDto;
import com.weatherappbackend.weather.weeksummary.SummaryFacade;
import com.weatherappbackend.weather.weeksummary.SummaryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class WeatherControllerTest {

    private WeatherController weatherController;

    @BeforeEach
    void setUp() {
        Forecast forecast = new ForecastService();
        Summary summary = new SummaryService();

        weatherController = new WeatherController(
                new ForecastFacade(new ClientService(new WeatherProxyTestImpl()), forecast),
                new SummaryFacade(new ClientService(new WeatherProxyTestImpl()), summary)
        );
    }

    @Test
    public void weather_forecast_controller_test() {
        ResponseEntity<List<ForecastDayDto>> response = weatherController.getWeatherForecast(52.52, 13.41);
        List<ForecastDayDto> forecastDaysDto = response.getBody();
        System.out.println(forecastDaysDto);

        assert forecastDaysDto != null;
        assertThat(forecastDaysDto.size()).isEqualTo(7);
        assertThat(forecastDaysDto.get(0).date().matches("\\d{2}.\\d{2}.\\d{4}")).isTrue();
        assertThat(forecastDaysDto.get(0).generatedPVEnergyKWH()).isEqualTo(5.0);
    }

    @Test
    public void week_summary_controller_test() {
        ResponseEntity<SummaryDto> response = weatherController.getWeekSummary(52.52, 13.41);
        SummaryDto summaryDto = response.getBody();
        System.out.println(summaryDto);

        assert summaryDto != null;
        assertThat(summaryDto.avgPressure()).isEqualTo(5.0);
        assertThat(summaryDto.avgSunTimeExposure()).isEqualTo(500.0);
        assertThat(summaryDto.minWeekTempC()).isEqualTo(-7.5);
        assertThat(summaryDto.maxWeekTempC()).isEqualTo(8.0);
        assertThat(summaryDto.description()).containsKey("precipitation");
        assertThat(summaryDto.description().get("precipitation")).isTrue();
        assertThat(summaryDto.description()).containsKey("wind");
        assertThat(summaryDto.description().get("wind")).isFalse();
    }
}