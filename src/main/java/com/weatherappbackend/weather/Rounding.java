package com.weatherappbackend.weather;

public interface Rounding {

    default Double round(Double number){
        return Math.round(number * 100.0) / 100.0;
    }
}
