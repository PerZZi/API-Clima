package com.example.ApiClima.services.implement;

import com.example.ApiClima.dtos.ForecastDTO;
import com.example.ApiClima.dtos.GeolocationDTO;
import com.example.ApiClima.dtos.WeatherDTO;
import com.example.ApiClima.services.WeatherService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@Service
public class WeatherServiceImplement implements WeatherService {

    @Value("${api_key}")
    private String api_id;
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private CacheManager cacheManager;

    @Override
    @Cacheable(cacheNames= "currentWeather", key = "#cityName")
    public WeatherDTO getWeatherByCityName(String cityName) {

        String BASE_URL = "https://api.openweathermap.org/data/2.5/weather?q=" + cityName + "&units=metric" + "&lang=es" + "&appid="+ api_id;

        ResponseEntity<String> response = restTemplate.getForEntity(BASE_URL, String.class);

        // Convier la respuesta JSON a un objeto WeatherData
        ObjectMapper objectMapper = new ObjectMapper();
        WeatherDTO weatherDTO = null;
        try {
            weatherDTO = objectMapper.readValue(response.getBody(), WeatherDTO.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return weatherDTO;
    }

    @Override
    @Cacheable(cacheNames = "forecast", key = "#cityName")
    public ForecastDTO getExtendedForecast(String cityName) {

        String FORECAST_URL = "https://api.openweathermap.org/data/2.5/forecast?q=" + cityName + "&appid=" + api_id;

        ResponseEntity<String> response = restTemplate.getForEntity(FORECAST_URL, String.class);

        ObjectMapper objectMapper = new ObjectMapper();
        ForecastDTO forecastDTO = null;

        try {
            forecastDTO = objectMapper.readValue(response.getBody(), ForecastDTO.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return forecastDTO;
    }

    @Override
    @Cacheable(cacheNames = "geolocation", key = "#cityName")
    public List<GeolocationDTO> getGeolocationByCityName(String cityName) {

        String GEO_URL = "http://api.openweathermap.org/geo/1.0/direct?q=" + cityName + "&limit=5" + "&appid=" + api_id;

        ResponseEntity<String> response = restTemplate.getForEntity(GEO_URL, String.class);

        // Convertir la respuesta JSON a una lista de GeolocationDTO
        ObjectMapper objectMapper = new ObjectMapper();
        List<GeolocationDTO> geolocationDTO = null;
        try {
            geolocationDTO = objectMapper.readValue(response.getBody(), List.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error procesando la respuesta de la API de geolocalización", e);
        }

        return geolocationDTO;
    }

    @Override
    public void clearCacheWeather() {
        Cache cache = cacheManager.getCache("currentWeather");
        if(cache != null){
            cache.clear();
        }
    }

    @Override
    public void clearCacheForecast() {
        Cache cache = cacheManager.getCache("forecast");
        if (cache != null){
            cache.clear();
        }
    }

    @Override
    public void clearCacheGeolocation() {
        Cache cache = cacheManager.getCache("geolocation");
        if(cache != null){
            cache.clear();
        }
    }
}
