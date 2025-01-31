package com.weatherappbackend.weather.forecast;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;


class ForecastServiceTest {

    private final ForecastService forecastService = new ForecastService();

    @Test
    void count_energy_for_1_hour() {
        Double v = forecastService.countEnergy(new BigDecimal("3600"));
        assertThat(v).isEqualTo(0.5);
    }

    @Test
    void count_energy_for_test_data_7956_sec() {
        Double v = forecastService.countEnergy(new BigDecimal("7956"));
        assertThat(v).isEqualTo(1.11);
    }

    @Test
    void count_energy_for_test_data_15683_76_sec() {
        Double v = forecastService.countEnergy(new BigDecimal("15683.76"));
        assertThat(v).isEqualTo(2.18);
    }

    @Test
    void count_energy_for_test_data_716_22_sec() {
        Double v = forecastService.countEnergy(new BigDecimal("716.22"));
        assertThat(v).isEqualTo(0.1);
    }
}