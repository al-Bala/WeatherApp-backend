package com.weatherappbackend.clientweatherapi;

import com.weatherappbackend.clientweatherapi.response.ClientResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class ClientService {
    private final WeatherProxy proxy;

    public ClientResponse getWeatherFor7Days(double latitude, double longitude){
        String[] params = {"weather_code", "temperature_2m_max", "temperature_2m_min", "sunshine_duration"};
        return proxy.makeQueryFor7DaysWeatherForecast(latitude, longitude, params);
    }
}
