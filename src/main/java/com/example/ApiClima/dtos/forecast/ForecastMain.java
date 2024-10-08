package com.example.ApiClima.dtos.forecast;

import lombok.Data;

@Data
public class ForecastMain {

    private double temp;
    private double feels_like;
    private double temp_min;
    private double temp_max;
    private short pressure;
    private short sea_level;
    private short grnd_level;
    private short humidity;
    private double temp_kf;
}
