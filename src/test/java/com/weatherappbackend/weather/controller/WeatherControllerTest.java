package com.weatherappbackend.weather.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.weatherappbackend.weather.forecast.ForecastDayDto;
import com.weatherappbackend.weather.weeksummary.SummaryDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockserver.client.MockServerClient;
import org.mockserver.model.Header;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.testcontainers.containers.MockServerContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;
import static org.mockserver.model.JsonBody.json;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Testcontainers
public class WeatherControllerTest {

    @Container
    static MockServerContainer mockServerContainer = new MockServerContainer(
            DockerImageName.parse("mockserver/mockserver:5.15.0")
    );

    static MockServerClient mockServerClient;

    @Autowired
    public MockMvc mockMvc;

    @Autowired
    public ObjectMapper objectMapper;

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
    void forecast_controller_happy_path() throws Exception {
        double latitudeValue = 52.52;
        double longitudeValue = 13.41;

        mockServerClient
                .when(
                        request().withMethod("GET")
                                .withPath("/v1/forecast")
                                .withQueryStringParameter("latitude", String.valueOf(latitudeValue))
                                .withQueryStringParameter("longitude", String.valueOf(longitudeValue))
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

        ResultActions forecastPerform = mockMvc
                .perform(get("/weather-forecast?latitude="+ latitudeValue + "&longitude=" + longitudeValue));

        MvcResult foreactMvcResult = forecastPerform.andExpect(status().isOk()).andReturn();
        String forecastAsString = foreactMvcResult.getResponse().getContentAsString();
        List<ForecastDayDto> forecastDaysDto = objectMapper.readValue(forecastAsString, new TypeReference<List<ForecastDayDto>>() {});
        System.out.println("Result: " + forecastDaysDto);

        assertThat(forecastDaysDto).hasSize(7);
    }

    @Test
    void forecast_controller_param_out_of_range() throws Exception {
        double latitudeValue = 52.52;
        double longitudeValue = -190.5;

        ResultActions forecastPerform = mockMvc
                .perform(get("/weather-forecast?latitude="+ latitudeValue + "&longitude=" + longitudeValue));

        forecastPerform.andExpect(status().isBadRequest());
    }

    @Test
    void forecast_controller_wrong_param_type() throws Exception {
        String latitudeValue = "string";
        double longitudeValue = 13.41;

        ResultActions forecastPerform = mockMvc
                .perform(get("/weather-forecast?latitude="+ latitudeValue + "&longitude=" + longitudeValue));

        ResultActions wrongParamResult = forecastPerform.andExpect(status().isBadRequest());
        String errorMessage = wrongParamResult.andReturn().getResponse().getContentAsString();
        System.out.println("Message: " + errorMessage);
    }

    @Test
    void summary_controller_happy_path() throws Exception {
        double latitudeValue = 52.52;
        double longitudeValue = 13.41;

        mockServerClient
                .when(
                        request().withMethod("GET")
                                .withPath("/v1/forecast")
                                .withQueryStringParameter("latitude", String.valueOf(latitudeValue))
                                .withQueryStringParameter("longitude", String.valueOf(longitudeValue))
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

        ResultActions summaryPerform  = mockMvc
                .perform(get("/week-summary?latitude="+ latitudeValue + "&longitude=" + longitudeValue));

        MvcResult summaryMvcResult  = summaryPerform.andExpect(status().isOk()).andReturn();
        String summaryAsString  = summaryMvcResult .getResponse().getContentAsString();
        SummaryDto summaryDto = objectMapper.readValue(summaryAsString, SummaryDto.class);
        System.out.println("Result: " + summaryDto);

        assertThat(summaryDto.avgPressure()).isEqualTo(1041.0);
        assertThat(summaryDto.avgSunTimeExposure()).isEqualTo(16024.0);
        assertThat(summaryDto.minWeekTempC()).isEqualTo(-0.9);
        assertThat(summaryDto.maxWeekTempC()).isEqualTo(6.1);
        assertThat(summaryDto.description()).containsKey("precipitation");
        assertThat(summaryDto.description().get("precipitation")).isFalse();
        assertThat(summaryDto.description()).containsKey("wind");
        assertThat(summaryDto.description().get("wind")).isFalse();
    }

    @Test
    void summary_controller_param_out_of_range() throws Exception {
        double latitudeValue = 91.0;
        double longitudeValue = 13.41;

        ResultActions forecastPerform = mockMvc
                .perform(get("/week-summary?latitude="+ latitudeValue + "&longitude=" + longitudeValue));

        forecastPerform.andExpect(status().isBadRequest());
    }

    @Test
    void summary_controller_wrong_param_type() throws Exception {
        double latitudeValue = 52.52;
        String longitudeValue = "string";

        ResultActions forecastPerform = mockMvc
                .perform(get("/week-summary?latitude="+ latitudeValue + "&longitude=" + longitudeValue));

        ResultActions wrongParamResult = forecastPerform.andExpect(status().isBadRequest());
        String errorMessage = wrongParamResult.andReturn().getResponse().getContentAsString();
        System.out.println("Message: " + errorMessage);
    }
}
