package com.example.ApiClima.controller;

import com.example.ApiClima.dtos.ForecastDTO;
import com.example.ApiClima.dtos.GeolocationDTO;
import com.example.ApiClima.dtos.WeatherDTO;
import com.example.ApiClima.services.WeatherService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/weather")
public class WeatherController {

    @Autowired
    private WeatherService weatherService;


    @Operation(summary = "Obtiene el clima actual de una ciudad o lugar", responses = {
            @ApiResponse(responseCode = "200", description = "Ok")
    })
    @GetMapping("/{cityName}")
    public ResponseEntity<WeatherDTO> getWeatherByCityName(@PathVariable String cityName) {
        WeatherDTO weatherDTO = weatherService.getWeatherByCityName(cityName);
        return ResponseEntity.ok(weatherDTO);
    }

    @Operation(summary = "Obtiene el pronostico extendido de 5 dias de una ciudad o lugar", responses = {
            @ApiResponse(responseCode = "200", description = "Ok")
    })
    @GetMapping("/forecast/{cityName}")
    public ResponseEntity<ForecastDTO> getForecast(@PathVariable String cityName) {
        ForecastDTO forecastDTO = weatherService.getExtendedForecast(cityName);
        return ResponseEntity.ok(forecastDTO);
    }

    @Operation(summary = "Obtiene la geolocalizacion de una ciudad o lugar", responses = {
            @ApiResponse(responseCode = "200", description = "Ok")
    })
    @GetMapping("/geo/{cityName}")
    public List<GeolocationDTO> getGeolocation(@PathVariable String cityName) {
        List<GeolocationDTO> geolocationDTOList = weatherService.getGeolocationByCityName(cityName);
        return geolocationDTOList;
    }

    @DeleteMapping("/delete/cacheWeather")
    public ResponseEntity<String> clearAllCacheWeather(){
        weatherService.clearCacheWeather();
        return ResponseEntity.ok("Cache for current weather has been cleared.");
    }

    @DeleteMapping("/delete/cacheForecast")
    public ResponseEntity<String> clearAllCacheForecast(){
        weatherService.clearCacheForecast();
        return ResponseEntity.ok("Cache for current forecast has been cleared.");
    }

    @DeleteMapping("/delete/cacheGeolocation")
    public ResponseEntity<String> clearAllCacheGeolocation(){
        weatherService.clearCacheGeolocation();
        return ResponseEntity.ok("Cache for current geolocation has been cleared.");
    }
}
