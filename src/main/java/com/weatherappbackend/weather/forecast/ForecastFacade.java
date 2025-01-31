package com.weatherappbackend.weather.forecast;

import com.weatherappbackend.clientweatherapi.ClientService;
import com.weatherappbackend.clientweatherapi.response.forecast.ForecastClientResponse;
import com.weatherappbackend.clientweatherapi.response.forecast.ForecastDaily;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Component
public class ForecastFacade {
    private final ClientService clientService;
    private final Forecast forecastService;

    public List<ForecastDayDto> getWeatherForecast(Double latitude, Double longitude){
        ForecastClientResponse forecastClientResponse = clientService.fetchForecastWeather(latitude, longitude);

        int daysNr = forecastClientResponse.daily().date().size();
        List<ForecastDayDto> forecastDays = new ArrayList<>();

        for(int i=0; i<daysNr; i++) {
            ForecastDaily response = forecastClientResponse.daily();
            ForecastDayDto dayForecast = ForecastDayDto.builder()
                    .date(formatDate(response.date().get(i)))
                    .code(response.weatherCode().get(i))
                    .maxDayTempC(response.temperatureMax().get(i))
                    .minDayTempC(response.temperatureMin().get(i))
                    .generatedPVEnergyKWH(forecastService.countEnergy(BigDecimal.valueOf(response.sunshineDurationSec()[i])))
                    .build();
            forecastDays.add(dayForecast);
        }
        return forecastDays;
    }

    private String formatDate(LocalDate date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        return formatter.format(date);
    }

}
