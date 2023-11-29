package com.senla.weather.service;

import com.senla.weather.model.Average;
import com.senla.weather.model.Weather;

import java.util.List;

public interface AverageService {

    Average getAverage(List<Weather> weatherList);
}
