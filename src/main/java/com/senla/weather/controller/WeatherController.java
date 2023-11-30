package com.senla.weather.controller;

import com.senla.weather.response.WeatherResponse;
import com.senla.weather.response.AverageWeatherResponse;
import com.senla.weather.request.RequestWeather;
import com.senla.weather.model.Weather;
import com.senla.weather.service.WeatherService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/weather")
@Slf4j
@RequiredArgsConstructor
public class WeatherController {

    private final WeatherService weatherService;

    @PostMapping("/average")
    public ResponseEntity<AverageWeatherResponse> getAverageWeatherData(@RequestBody @Valid RequestWeather requestWeather){
        List<Weather> weatherList = weatherService.getPeriodTimeWeather(requestWeather.getStartDate(), requestWeather.getEndDate());
        //TODO: extract to weather service
        AverageWeatherResponse averageWeatherResponse = weatherService.getAverage(weatherList);
        return ResponseEntity.ok(averageWeatherResponse);
    }

    @GetMapping("/current")
    public ResponseEntity<WeatherResponse> getLatestWeatherData(){
        WeatherResponse weatherResponse = weatherService.getLatestWeatherData();
        return ResponseEntity.ok(weatherResponse);
    }
}
