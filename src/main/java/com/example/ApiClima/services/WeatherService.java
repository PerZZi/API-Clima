package com.example.ApiClima.services;

import com.example.ApiClima.dtos.ForecastDTO;
import com.example.ApiClima.dtos.GeolocationDTO;
import com.example.ApiClima.dtos.WeatherDTO;

import java.util.List;

public interface WeatherService {

    WeatherDTO getWeatherByCityName(String cityName);
    ForecastDTO getExtendedForecast(String cityName);
    List<GeolocationDTO> getGeolocationByCityName(String cityName);
    void clearCacheWeather();
    void clearCacheForecast();
    void clearCacheGeolocation();
}
