package com.senla.weather.service.impl;

import com.senla.weather.model.Weather;
import com.senla.weather.pojo.WeatherFromAPI;
import com.senla.weather.repo.WeatherRepository;
import com.senla.weather.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WeatherServiceImpl implements WeatherService {

    @Autowired
    private WeatherRepository weatherRepository;
    @Override
    public void create(WeatherFromAPI weatherFromAPI) {
        Weather weather = new Weather();
        weather.setMph(weatherFromAPI.current.windMph);
        weather.setHumidity(weatherFromAPI.current.humidity);
        weather.setPressure(weatherFromAPI.current.pressureMb);
        weather.setTemperature(weatherFromAPI.current.tempC);
        weather.setLocation(weatherFromAPI.location.name);
        weather.setWeatherCondition(weatherFromAPI.current.condition.text);
        weatherRepository.save(weather);
    }
}
