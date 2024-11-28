package com.weatherappbackend.weather;

import java.util.List;

public interface Avg {
    default double getAvg(List<Double> values) {
        if(values.isEmpty())
            return 0;

        double sum = values.stream()
                .mapToDouble(Double::doubleValue)
                .sum();
        return sum / values.size();
    }
}
