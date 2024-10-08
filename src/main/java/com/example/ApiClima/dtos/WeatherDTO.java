package com.example.ApiClima.dtos;

import lombok.Data;

@Data
public class WeatherDTO {

    private long id;
    private String name;
    private long cod;
    private long timezone;
    private Coord coord;
    private WeatherElement[] weather;
    private String base;
    private Main main;
    private long visibility;
    private Wind wind;
    private Clouds clouds;
    private long dt;
    private Sys sys;
}
