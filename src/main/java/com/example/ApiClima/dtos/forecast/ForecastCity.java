package com.example.ApiClima.dtos.forecast;

import lombok.Data;

@Data
public class ForecastCity {

    private long id;
    private String name;
    private ForecastCoord coord;
    private String country;
    private long population;
    private long timezone;
    private long sunrise;
    private long sunset;
}
