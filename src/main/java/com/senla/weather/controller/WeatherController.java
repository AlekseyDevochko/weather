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

    /**
     * Retrieves the average weather data for a specified period and returns it as a ResponseEntity.
     *
     * @param requestWeather the RequestWeather object containing the start and end dates
     * @return a ResponseEntity containing the AverageWeatherResponse with the average weather data
     */
    @PostMapping("/average")
    public ResponseEntity<AverageWeatherResponse> getAverageWeatherData(@RequestBody @Valid RequestWeather requestWeather){
        List<Weather> weatherList = weatherService.getPeriodTimeWeather(requestWeather.getStartDate(), requestWeather.getEndDate());
        AverageWeatherResponse averageWeatherResponse = weatherService.getAverage(weatherList);
        return ResponseEntity.ok(averageWeatherResponse);
    }

    /**
     * Retrieves the latest weather data and returns it as a ResponseEntity.
     *
     * @return a ResponseEntity containing the WeatherResponse with the latest weather data
     */
    @GetMapping("/current")
    public ResponseEntity<WeatherResponse> getLatestWeatherData(){
        WeatherResponse weatherResponse = weatherService.getLatestWeatherData();
        return ResponseEntity.ok(weatherResponse);
    }
}
