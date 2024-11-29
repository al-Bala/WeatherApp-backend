package com.weatherappbackend.weather.weeksummary;

import com.weatherappbackend.weather.Avg;
import com.weatherappbackend.weather.Rounding;
import com.weatherappbackend.weather.weeksummary.description.DescriptionElement;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class SummaryService implements Summary, Avg, Rounding {

    @Override
    public double countAvg(List<Double> values) {
        double avg = getAvg(values);
        return round(avg);
    }

    @Override
    public double chooseMax(List<Double> values) {
        return values.stream()
                .max(Double::compareTo)
                .orElse(0.0001);
    }

    @Override
    public double chooseMin(List<Double> values) {
        return values.stream()
                .min(Double::compareTo)
                .orElse(0.0001);
    }

    @Override
    public Map<String, Boolean> createDescription(List<DescriptionElement> descElements) {
        Map<String, Boolean> description = new HashMap<>();

        for(DescriptionElement descElement : descElements) {
            if(!descElement.getValues().isEmpty())
                addToDescription(descElement, description);
        }
        if(description.isEmpty())
            description.put("default", true);
        return description;
    }

    private void addToDescription(DescriptionElement descElement, Map<String, Boolean> description) {
        double value = descElement.count();
        boolean status = descElement.chooseStatus(value);
        description.put(descElement.getId(), status);
    }
}
