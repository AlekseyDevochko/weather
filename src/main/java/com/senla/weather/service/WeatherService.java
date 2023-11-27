package com.senla.weather.service;

import com.senla.weather.model.Weather;
import com.senla.weather.pojo.WeatherFromAPI;

public interface WeatherService {
    void create(WeatherFromAPI weatherFromAPI);
}
