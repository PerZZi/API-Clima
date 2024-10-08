package com.example.ApiClima.dtos;

import lombok.Data;

import java.util.Map;
@Data
public class GeolocationDTO {

    private String name;
    private Map<String,String>[] local_names;
    private Coord coord;
    private String country;
    private String state;
}
