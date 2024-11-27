package com.weatherappbackend.weatherforecast.week;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class WeekTest {

    @Mock
    private Week week;

    // pressure_msl (hPa)
    // sunshine_duration (s)
    @Test
    void count_avg_from_list() {
        // given
        List<Double> values = new ArrayList<>(List.of(1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0));
        // when
        double avg = week.countAvg(values);
        // then
        assertEquals(5.0, avg);

    }

    @Test
    void choose_max_number_from_list() {
        // given
        List<Double> values = new ArrayList<>(List.of(1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 9.0, 9.0));
        // when
        double max = week.chooseMax(values);
        // then
        assertEquals(9.0, max);
    }

    @Test
    void choose_min_number_from_list() {
        // given
        List<Double> values = new ArrayList<>(List.of(1.0, 1.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0));
        // when
        double min = week.chooseMin(values);
        // then
        assertEquals(1.0, min);
    }

    @Test
    void add_default_value_when_given_values_are_empty() {
        // given
        Map<String, List<Double>> values = new HashMap<>();
        // when
        Map<String, Boolean> summary = week.generateSummary(values);
        // then
        assertThat(summary.containsKey("default")).isTrue();
    }

    @Test
    void skip_key_when_its_values_are_empty() {
        // given
        Map<String, List<Double>> values = new HashMap<>(Map.of(
                "precipitation", Collections.emptyList(),
                "wind", List.of(20.0, 20.0, 20.0, 20.0, 0.0, 0.0, 0.0)
        ));
        // when
        Map<String, Boolean> summary = week.generateSummary(values);
        // then
        assertThat(summary.containsKey("precipitation")).isFalse();
        assertThat(summary.containsKey("wind")).isTrue();
    }

    // precipitation_sum (mm): 4 days * 5 mm = 20 mm (<= sum)
    // 5 mm - rainy day
    // wind (km/h): 4 days * 20 km/h = 80 km/h (<= sum)
    // avg 20 km/h - windy day
    @Test
    void add_precipitation_true_when_sum_is_20_mm_and_wind_true_when_sum_is_80_kmh() {
        // given
        Map<String, List<Double>> values = new HashMap<>(Map.of(
                "precipitation", List.of(10.0, 10.0, 0.0, 0.0, 8.0, 10.0, 10.0),
                "wind", List.of(20.0, 20.0, 20.0, 20.0, 0.0, 0.0, 0.0)
        ));
        // when
        Map<String, Boolean> summary = week.generateSummary(values);
        // then
        assertThat(summary.get("precipitation")).isTrue();
        assertThat(summary.get("wind")).isTrue();
    }

    @Test
    void add_precipitation_false_when_sum_is_less_then_20_mm() {
        // given
        Map<String, List<Double>> values = new HashMap<>(Map.of(
                "precipitation", List.of(0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0),
                "wind", Collections.emptyList()
        ));
        // when
        Map<String, Boolean> summary = week.generateSummary(values);
        // then
        assertThat(summary.get("precipitation")).isFalse();
    }

    @Test
    void add_wind_false_when_sum_is_less_then_80_kmh() {
        // given
        Map<String, List<Double>> values = new HashMap<>(Map.of(
                "precipitation", Collections.emptyList(),
                "wind", List.of(0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0)
        ));
        // when
        Map<String, Boolean> summary = week.generateSummary(values);
        // then
        assertThat(summary.get("wind")).isFalse();
    }
}