package com.weatherappbackend.weather.controller;

import com.weatherappbackend.weather.forecast.ForecastDayDto;
import com.weatherappbackend.weather.forecast.ForecastFacade;
import com.weatherappbackend.weather.weeksummary.SummaryDto;
import com.weatherappbackend.weather.weeksummary.SummaryFacade;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockserver.client.MockServerClient;
import org.mockserver.model.Header;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MockServerContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;
import static org.mockserver.model.JsonBody.json;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
public class WeatherControllerTest {
    private final static double LATITUDE_VALUE = 52.52;
    private final static double LONGITUDE_VALUE = 13.41;

    @Container
    static MockServerContainer mockServerContainer = new MockServerContainer(
            DockerImageName.parse("mockserver/mockserver:5.15.0")
    );

    static MockServerClient mockServerClient;

    @Autowired
    private ForecastFacade forecastFacade;

    @Autowired
    private SummaryFacade summaryFacade;

    @DynamicPropertySource
    static void overrideProperties(DynamicPropertyRegistry registry) {
        mockServerClient =
                new MockServerClient(
                        mockServerContainer.getHost(),
                        mockServerContainer.getServerPort()
                );
        registry.add("weather-client.url", mockServerContainer::getEndpoint);
    }

    @BeforeEach
    void setUp() {
        mockServerClient.reset();
    }

    @Test
    void weather_forecast_controller_test() {
        mockServerClient
                .when(
                        request().withMethod("GET")
                                .withPath("/v1/forecast")
                                .withQueryStringParameter("latitude", String.valueOf(LATITUDE_VALUE))
                                .withQueryStringParameter("longitude", String.valueOf(LONGITUDE_VALUE))
                                .withQueryStringParameter("daily", "weather_code,temperature_2m_max,temperature_2m_min,sunshine_duration")
                )
                .respond(
                        response()
                                .withStatusCode(200)
                                .withHeaders(
                                        new Header("Content-Type", "application/json; charset=utf-8")
                                )
                                .withBody(
                                        json(
                                                """
                                                {"daily": {
                                                         "time": [
                                                             "2025-01-31",
                                                             "2025-02-01",
                                                             "2025-02-02",
                                                             "2025-02-03",
                                                             "2025-02-04",
                                                             "2025-02-05",
                                                             "2025-02-06"
                                                         ],
                                                         "weather_code": [
                                                             80,
                                                             45,
                                                             45,
                                                             45,
                                                             2,
                                                             3,
                                                             3
                                                         ],
                                                         "temperature_2m_max": [
                                                             5.8,
                                                             2.4,
                                                             2.0,
                                                             1.7,
                                                             4.3,
                                                             4.3,
                                                             4.8
                                                         ],
                                                         "temperature_2m_min": [
                                                             2.2,
                                                             0.3,
                                                             -0.4,
                                                             -1.6,
                                                             -1.0,
                                                             -0.1,
                                                             1.3
                                                         ],
                                                         "sunshine_duration": [
                                                             5828.42,
                                                             1515.97,
                                                             9079.19,
                                                             19532.70,
                                                             25717.62,
                                                             28872.84,
                                                             1251.68
                                                         ]
                                                     }}
                                                """
                                        )
                                )
                );

        List<ForecastDayDto> weatherForecast = forecastFacade.getWeatherForecast(LATITUDE_VALUE, LONGITUDE_VALUE);
        System.out.println(weatherForecast);

        assertThat(weatherForecast).hasSize(7);
    }

    @Test
    void week_summary_controller_test() {
        mockServerClient
                .when(
                        request().withMethod("GET")
                                .withPath("/v1/forecast")
                                .withQueryStringParameter("latitude", String.valueOf(LATITUDE_VALUE))
                                .withQueryStringParameter("longitude", String.valueOf(LONGITUDE_VALUE))
                                .withQueryStringParameter("hourly", "pressure_msl,wind_speed_10m")
                                .withQueryStringParameter("daily", "sunshine_duration,temperature_2m_max,temperature_2m_min,precipitation_sum")
                )
                .respond(
                        response()
                                .withStatusCode(200)
                                .withHeaders(
                                        new Header("Content-Type", "application/json; charset=utf-8")
                                )
                                .withBody(
                                        json(
                                                """
                                                {"hourly": {
                                                         "pressure_msl": [
                                                             1041.2
                                                         ],
                                                         "wind_speed_10m": [
                                                             16.5
                                                         ]
                                                     },
                                                     "daily": {
                                                         "sunshine_duration": [
                                                             16164.86,
                                                             6165.52,
                                                             12397.30,
                                                             18792.53,
                                                             28523.99,
                                                             28872.84,
                                                             1251.68
                                                         ],
                                                         "temperature_2m_max": [
                                                             6.1,
                                                             3.3,
                                                             2.7,
                                                             1.8,
                                                             4.0,
                                                             4.3,
                                                             4.8
                                                         ],
                                                         "temperature_2m_min": [
                                                             2.4,
                                                             0.3,
                                                             0.1,
                                                             -0.4,
                                                             -0.9,
                                                             -0.3,
                                                             1.3
                                                         ],
                                                         "precipitation_sum": [
                                                             1.90,
                                                             0.00,
                                                             0.00,
                                                             0.00,
                                                             0.00,
                                                             0.00,
                                                             0.30
                                                         ]
                                                     }}
                                                """
                                        )
                                )
                );

        SummaryDto weekSummary = summaryFacade.getWeekSummary(LATITUDE_VALUE, LONGITUDE_VALUE);
        System.out.println(weekSummary);

        assertThat(weekSummary.avgPressure()).isEqualTo(1041.0);
        assertThat(weekSummary.avgSunTimeExposure()).isEqualTo(16024.0);
        assertThat(weekSummary.minWeekTempC()).isEqualTo(-0.9);
        assertThat(weekSummary.maxWeekTempC()).isEqualTo(6.1);
        assertThat(weekSummary.description()).containsKey("precipitation");
        assertThat(weekSummary.description().get("precipitation")).isFalse();
        assertThat(weekSummary.description()).containsKey("wind");
        assertThat(weekSummary.description().get("wind")).isFalse();
    }
}
