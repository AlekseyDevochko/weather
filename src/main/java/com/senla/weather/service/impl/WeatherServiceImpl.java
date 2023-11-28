package com.senla.weather.service.impl;

import com.senla.weather.model.AverageTemperature;
import com.senla.weather.model.Weather;
import com.senla.weather.pojo.WeatherFromAPI;
import com.senla.weather.repo.WeatherRepository;
import com.senla.weather.service.WeatherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//import java.util.Date;

import java.sql.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;


@Service
@Slf4j
public class WeatherServiceImpl implements WeatherService {


    private WeatherRepository weatherRepository;
    @Transactional
    @Override
    public void create(WeatherFromAPI weatherFromAPI) {
        Weather weather = new Weather();
        weather.setMph(weatherFromAPI.current.windMph);
        weather.setHumidity(weatherFromAPI.current.humidity);
        weather.setPressure(weatherFromAPI.current.pressureMb);
        weather.setTemperature(weatherFromAPI.current.tempC);
        weather.setLocation(weatherFromAPI.location.name);
        weather.setWeatherCondition(weatherFromAPI.current.condition.text);
        weather.setDate(new Date(weatherFromAPI.location.localtimeEpoch*1000));
        weatherRepository.save(weather);
        log.info("new weather created");
    }

    @Override
    public List<Weather> getWeatherList(Date startDate, Date endDate) {
        List<Weather> weatherList = weatherRepository.getWeatherFromStartToEnd(startDate, endDate);
        log.info("start and end dates weather list got");
        return weatherList;
    }

    @Override
    public AverageTemperature calculateAverageTemp(List<Weather> weatherList) {
        AtomicReference<Integer> sum = new AtomicReference<>(0);
        weatherList.forEach(weather -> sum.updateAndGet(v -> v + weather.getTemperature()));
        Integer result = sum.get() / weatherList.size();
        log.info("calculate average temperature");
        return new AverageTemperature(result);
    }


    @Autowired
    public void setWeatherRepository(WeatherRepository weatherRepository) {
        this.weatherRepository = weatherRepository;
    }
}
