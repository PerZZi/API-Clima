package com.example.ApiClima.dtos.forecast;

import lombok.Data;

@Data
public class ForecastWeather {
    private long id;
    private String main;
    private String description;
    private String icon;
}
