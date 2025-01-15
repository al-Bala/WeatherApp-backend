package weatherappbackend.clientweatherapi;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.weatherappbackend.WeatherBackendApplication;
import com.weatherappbackend.weather.forecast.ForecastDayDto;
import com.weatherappbackend.weather.weeksummary.SummaryDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = WeatherBackendApplication.class)
@AutoConfigureMockMvc
public class ClientApiIntegrationTest {

    @Autowired
    public MockMvc mockMvc;

    @Autowired
    public ObjectMapper objectMapper;

    @Test
    public void connect_with_api_for_weather_forecast() throws Exception {
        ResultActions forecastPerform = mockMvc.perform(get("/weather-forecast?latitude=52.52&longitude=13.41"));

        MvcResult foreactMvcResult = forecastPerform.andExpect(status().isOk()).andReturn();
        String forecastAsString = foreactMvcResult.getResponse().getContentAsString();
        List<ForecastDayDto> forecastDaysDto = objectMapper.readValue(forecastAsString, new TypeReference<List<ForecastDayDto>>() {});
        System.out.println(forecastDaysDto);

        assertThat(forecastDaysDto.size()).isEqualTo(7);
    }

    @Test
    public void connect_with_api_for_week_summary() throws Exception {
        ResultActions summaryPerform = mockMvc.perform(get("/week-summary?latitude=52.52&longitude=13.41"));

        MvcResult summaryMvcResult = summaryPerform.andExpect(status().isOk()).andReturn();
        String summaryAsString = summaryMvcResult.getResponse().getContentAsString();
        SummaryDto summaryDto = objectMapper.readValue(summaryAsString, SummaryDto.class);
        System.out.println(summaryDto);
    }
}
