package com.weatherappbackend.weather.weeksummary;

import com.weatherappbackend.clientweatherapi.ClientService;
import com.weatherappbackend.clientweatherapi.response.summary.SummaryClientResponse;
import com.weatherappbackend.weather.weeksummary.description.DescriptionElement;
import com.weatherappbackend.weather.weeksummary.description.Precipitation;
import com.weatherappbackend.weather.weeksummary.description.Wind;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@AllArgsConstructor
@Component
public class SummaryFacade {
    private final ClientService clientService;
    private final Summary summaryService;

    public SummaryDto getWeekSummary(Double latitude, Double longitude) {
        SummaryClientResponse summaryClientResponse = clientService.fetchSummaryWeather(latitude, longitude);

        double avgPressure = summaryService.countAvg(summaryClientResponse.hourly().pressureMsl());
        double avgSunTimeExposureSec = summaryService.countAvg(summaryClientResponse.daily().sunshineDurationSec());
        double minWeekTempC = summaryService.chooseMin(summaryClientResponse.daily().temperatureMin());
        double maxWeekTempC = summaryService.chooseMax(summaryClientResponse.daily().temperatureMax());

        List<DescriptionElement> descriptionElements = mapToDescElements(summaryClientResponse);
        Map<String, Boolean> description = summaryService.createDescription(descriptionElements);

        return SummaryDto.builder()
                .avgPressure(avgPressure)
                .avgSunTimeExposure(avgSunTimeExposureSec)
                .minWeekTempC(minWeekTempC)
                .maxWeekTempC(maxWeekTempC)
                .description(description)
                .build();
    }

    private static List<DescriptionElement> mapToDescElements(SummaryClientResponse summaryClientResponse) {
        double[] precipitationSum = summaryClientResponse.daily().precipitationSum();
        double[] windSpeed = summaryClientResponse.hourly().windSpeed();

        return List.of(
                new Precipitation("precipitation", precipitationSum),
                new Wind("wind", windSpeed)
        );
    }
}
