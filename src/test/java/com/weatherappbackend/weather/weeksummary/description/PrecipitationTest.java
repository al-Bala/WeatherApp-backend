package com.weatherappbackend.weather.weeksummary.description;

import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class PrecipitationTest {

    @Test
    void choose_values_greater_than_one() {
        Precipitation p = new Precipitation("p", List.of(0.0, 2.2, 1.0, 0.5));
        double count = p.count();
        assertThat(count).isEqualTo(1);
    }

    @Test
    void return_zero_when_not_find_any_greater_than_one() {
        Precipitation p = new Precipitation("p", List.of(0.0, 0.1, 1.0, 0.5));
        double count = p.count();
        assertThat(count).isEqualTo(0);
    }

    @Test
    void return_zero_when_list_is_empty() {
        Precipitation p = new Precipitation("p", Collections.emptyList());
        double count = p.count();
        assertThat(count).isEqualTo(0);
    }
}