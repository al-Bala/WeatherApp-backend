package com.weatherappbackend.clientweatherapi;

import com.weatherappbackend.clientweatherapi.response.forecast.ForecastClientResponse;
import com.weatherappbackend.clientweatherapi.response.summary.SummaryClientResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class ClientService {
    private final WeatherProxy proxy;

    public ForecastClientResponse fetchForecastWeather(double latitude, double longitude){
        String[] dailyParams = {"weather_code", "temperature_2m_max", "temperature_2m_min", "sunshine_duration"};
        return proxy.makeQueryForForecastWeather(latitude, longitude, dailyParams);
    }

    public SummaryClientResponse fetchSummaryWeather(double latitude, double longitude){
        String[] hourlyParams = {"pressure_msl", "wind_speed_10m"};
        String[] dailyParams = {"sunshine_duration", "temperature_2m_max", "temperature_2m_min", "precipitation_sum"};
        return proxy.makeQueryForSummaryWeather(latitude, longitude, hourlyParams, dailyParams);
    }
}
