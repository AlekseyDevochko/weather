package com.senla.weather.service;

import com.senla.weather.response.WeatherResponse;
import com.senla.weather.response.AverageWeatherResponse;
import com.senla.weather.model.Weather;
import com.senla.weather.pojo.WeatherFromAPI;

import java.sql.Date;
import java.util.List;

public interface WeatherService {
    void create(WeatherFromAPI weatherFromAPI);
    List<Weather> getPeriodTimeWeather(Date startDate, Date endDate);

    WeatherResponse getLatestWeatherData();

    AverageWeatherResponse getAverage(List<Weather> weatherList);
}
