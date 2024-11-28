package com.weatherappbackend.weather.controller;

import com.weatherappbackend.clientweatherapi.ClientService;
import com.weatherappbackend.clientweatherapi.WeatherProxyTestImpl;
import com.weatherappbackend.weather.Weather;
import com.weatherappbackend.weather.forecast.Forecast;
import com.weatherappbackend.weather.weeksummary.Summary;
import com.weatherappbackend.weather.weeksummary.SummaryDto;
import com.weatherappbackend.weather.weeksummary.SummaryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

class WeatherControllerTest {

    private WeatherController weatherController;

    @BeforeEach
    void setUp() {
        Forecast mockForecast = mock(Forecast.class);
        Summary summary = new SummaryService();

        weatherController = new WeatherController(
                new ClientService(new WeatherProxyTestImpl()),
                new Weather(mockForecast, summary)
        );
    }

    @Test
    public void week_summary_controller_test() {
        ResponseEntity<SummaryDto> response = weatherController.getWeekSummary(52.52, 13.41);
        SummaryDto summaryDto = response.getBody();
        System.out.println(summaryDto);

        assert summaryDto != null;
        assertEquals(5.0, summaryDto.avgPressure());
        assertEquals(500.0, summaryDto.avgSunTimeExposure());
        assertEquals(-7.5, summaryDto.minWeekTempC());
        assertEquals(8.0, summaryDto.maxWeekTempC());
        assertThat(summaryDto.description()).containsKey("precipitation");
        assertEquals(true, summaryDto.description().get("precipitation"));
        assertThat(summaryDto.description()).containsKey("wind");
        assertEquals(false, summaryDto.description().get("wind"));
    }
}