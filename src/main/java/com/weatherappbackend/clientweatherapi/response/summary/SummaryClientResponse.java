package com.weatherappbackend.clientweatherapi.response.summary;

public record SummaryClientResponse(
        SummaryHourly hourly,
        SummaryDaily daily
) {
}
