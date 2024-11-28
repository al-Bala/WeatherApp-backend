package com.weatherappbackend.weather.weeksummary.description;

import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class WindTest {

    @Test
    void count_avg() {
        Wind w = new Wind("w", List.of(1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0));
        double count = w.count();
        assertThat(count).isEqualTo(5.0);
    }

    @Test
    void return_zero_when_list_is_empty() {
        Wind w = new Wind("w", Collections.emptyList());
        double count = w.count();
        assertThat(count).isEqualTo(0);
    }
}