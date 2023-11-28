package com.senla.weather.controller;

import com.senla.weather.model.AverageTemperature;
import com.senla.weather.model.RequestWeather;
import com.senla.weather.model.Weather;
import com.senla.weather.service.WeatherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@Slf4j
public class WeatherController {

    private WeatherService weatherService;



    @PostMapping("/get-list")
    public AverageTemperature getWeatherList(@RequestBody @Valid RequestWeather requestWeather){
        List<Weather> weatherList = weatherService.getWeatherList(requestWeather.getStartDate(), requestWeather.getEndDate());
        AverageTemperature result = weatherService.calculateAverageTemp(weatherList);
        log.info("/get-list");
        return ResponseEntity.ok(result).getBody();
    }

    @Autowired
    public void setWeatherService(WeatherService weatherService) {
        this.weatherService = weatherService;
    }
}
