package com.weatherappbackend.weather.weeksummary;

import com.weatherappbackend.weather.weeksummary.description.DescriptionElement;
import com.weatherappbackend.weather.weeksummary.description.Precipitation;
import com.weatherappbackend.weather.weeksummary.description.Wind;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class SummaryTest {
    private final Summary summary = new SummaryService();

    // pressure_msl (hPa)
    // sunshine_duration (s)
    @Test
    void count_avg_from_list() {
        // given
        List<Double> values = new ArrayList<>(List.of(1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0));
        // when
        double avg = summary.countAvg(values);
        // then
        assertEquals(5.0, avg);
    }

    @Test
    void choose_max_number_from_list() {
        // given
        List<Double> values = new ArrayList<>(List.of(1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 9.0, 9.0));
        // when
        double max = summary.chooseMax(values);
        // then
        assertEquals(9.0, max);
    }

    @Test
    void return_0_0001_when_list_of_max_or_min_is_empty() {
        // given
        List<Double> values = new ArrayList<>();
        // when
        double max = summary.chooseMax(values);
        double min = summary.chooseMin(values);
        // then
        assertEquals(0.0001, max);
        assertEquals(0.0001, min);
    }

    @Test
    void choose_min_number_from_list() {
        // given
        List<Double> values = new ArrayList<>(List.of(1.0, 1.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0));
        // when
        double min = summary.chooseMin(values);
        // then
        assertEquals(1.0, min);
    }

    @Test
    void add_default_value_when_given_values_are_empty() {
        // given
        List<DescriptionElement> descElements = new ArrayList<>();
        // when
        Map<String, Boolean> description = summary.createDescription(descElements);
        // then
        assertThat(description.containsKey("default")).isTrue();
    }

    @Test
    void skip_key_when_its_values_are_empty() {
        // given
        List<DescriptionElement> descElements = List.of(
                new Precipitation("precipitation", Collections.emptyList()),
                new Wind("wind", List.of(20.0, 20.0, 20.0, 20.0, 0.0, 0.0, 0.0))
        );
        // when
        Map<String, Boolean> description = summary.createDescription(descElements);
        // then
        assertThat(description.containsKey("precipitation")).isFalse();
        assertThat(description.containsKey("wind")).isTrue();
    }

    @Test
    void add_precipitation_true_when_is_4_or_more_rainy_days_and_wind_true_when_avg_is_20_kmh_or_more() {
        // given
        List<DescriptionElement> descElements = List.of(
                new Precipitation("precipitation", List.of(10.0, 10.0, 0.0, 0.0, 0.0, 10.0, 10.0)),
                new Wind("wind", List.of(20.0, 22.0))
        );
        // when
        Map<String, Boolean> description = summary.createDescription(descElements);
        // then
        assertThat(description.get("precipitation")).isTrue();
        assertThat(description.get("wind")).isTrue();
    }

    @Test
    void add_precipitation_false_when_is_less_then_4_rainy_days() {
        // given
        List<DescriptionElement> descElements = List.of(
                new Precipitation("precipitation", List.of(0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0)),
                new Wind("wind", Collections.emptyList())
        );
        // when
        Map<String, Boolean> description = summary.createDescription(descElements);
        // then
        assertThat(description.get("precipitation")).isFalse();
    }

    @Test
    void add_wind_false_when_avg_is_less_then_20_kmh() {
        // given
        List<DescriptionElement> descElements = List.of(
                new Precipitation("precipitation", Collections.emptyList()),
                new Wind("wind", List.of(0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0))
        );
        // when
        Map<String, Boolean> description = summary.createDescription(descElements);
        // then
        assertThat(description.get("wind")).isFalse();
    }
}