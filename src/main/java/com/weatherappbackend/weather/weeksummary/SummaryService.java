package com.weatherappbackend.weather.weeksummary;

import com.weatherappbackend.weather.weeksummary.description.DescriptionElement;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SummaryService implements Summary {

    @Override
    public double countAvg(double[] values) {
        double value = Arrays.stream(values)
                .average()
                .orElse(0);
        return Math.round(value);
    }

    @Override
    public double chooseMax(double[] values) {
        return Arrays.stream(values)
                .max()
                .orElse(0.0001);
    }

    @Override
    public double chooseMin(double[] values) {
        return Arrays.stream(values)
                .min()
                .orElse(0.0001);
    }

    @Override
    public Map<String, Boolean> createDescription(List<DescriptionElement> descElements) {
        Map<String, Boolean> description = new HashMap<>();

        for(DescriptionElement descElement : descElements) {
            if(descElement.getValues().length != 0)
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
