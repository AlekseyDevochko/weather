package com.senla.weather.controller;

import com.senla.weather.model.Average;
import com.senla.weather.model.RequestWeather;
import com.senla.weather.model.Weather;
import com.senla.weather.service.AverageService;
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
    private AverageService averageService;



    @PostMapping("/get-list")
    public Average getWeatherList(@RequestBody @Valid RequestWeather requestWeather){
        List<Weather> weatherList = weatherService.getWeatherList(requestWeather.getStartDate(), requestWeather.getEndDate());
        Average average = averageService.getAverage(weatherList);
        return ResponseEntity.ok(average).getBody();
    }

    @Autowired
    public void setWeatherService(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @Autowired
    public void setAverageService(AverageService averageService) {
        this.averageService = averageService;
    }
}
