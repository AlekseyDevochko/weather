package com.senla.weather.service;

import com.senla.weather.model.AverageTemperature;
import com.senla.weather.model.Weather;
import com.senla.weather.pojo.WeatherFromAPI;

import java.sql.Date;
import java.util.List;

public interface WeatherService {
    void create(WeatherFromAPI weatherFromAPI);
    List<Weather> getWeatherList(Date startDate, Date endDate);

    AverageTemperature calculateAverageTemp(List<Weather> weatherList);

}
