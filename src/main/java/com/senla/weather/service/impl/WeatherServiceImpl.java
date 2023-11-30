package com.senla.weather.service.impl;

import com.senla.weather.response.WeatherResponse;
import com.senla.weather.response.AverageWeatherResponse;
import com.senla.weather.model.Weather;
import com.senla.weather.pojo.WeatherFromAPI;
import com.senla.weather.repo.WeatherRepository;
import com.senla.weather.service.WeatherService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;


@Service
@Slf4j
@RequiredArgsConstructor
public class WeatherServiceImpl implements WeatherService {


    private final WeatherRepository weatherRepository;
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

    //@Transactional
    @Override
    public List<Weather> getPeriodTimeWeather(Date startDate, Date endDate) {
        List<Weather> weatherList = weatherRepository.getWeatherFromStartToEnd(startDate, endDate);
        log.info("start and end dates weather list got");
        return weatherList;
    }

    @Override
    public WeatherResponse getLatestWeatherData() {
        Weather weather = weatherRepository.getLatestCreatedWeatherData();
        return toDTO(weather);
    }

    private WeatherResponse toDTO(Weather weather){
        WeatherResponse weatherResponse = new WeatherResponse();
        weatherResponse.setTemperature(weather.getTemperature());
        weatherResponse.setMph(weather.getMph());
        weatherResponse.setPressure(weather.getPressure());
        weatherResponse.setHumidity(weather.getHumidity());
        weatherResponse.setWeatherCondition(weather.getWeatherCondition());
        weatherResponse.setLocation(weather.getLocation());
        weatherResponse.setDate(weather.getDate());
        weatherResponse.setTime(weather.getTime());
        return weatherResponse;
    }

    @Override
    public AverageWeatherResponse getAverage(List<Weather> weatherList) {
        AverageWeatherResponse averageWeatherResponse = new AverageWeatherResponse();

        averageWeatherResponse.setAverageTemperature(getAverageInteger(weatherList.stream().map(Weather::getTemperature).toList()));
        averageWeatherResponse.setAveragePressure(getAverageInteger(weatherList.stream().map(Weather::getPressure).toList()));
        averageWeatherResponse.setAverageHumidity(getAverageInteger(weatherList.stream().map(Weather::getHumidity).toList()));
        averageWeatherResponse.setAverageMph(getAverageDouble(weatherList.stream().map(Weather::getMph).toList()));
        averageWeatherResponse.setAverageWeatherCondition(getMostCommonWeatherCondition(weatherList.stream().map(Weather::getWeatherCondition).toList()));
        return averageWeatherResponse;
    }

    private String getMostCommonWeatherCondition(List<String> weatherConditionList){
        Map<String, Long> map= weatherConditionList.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        Optional<String> mostCommon = map.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey);
        return mostCommon.get();
    }

    private int getAverageInteger(List<Integer> list) {
        return (int) list.stream()
                .mapToInt(a -> a)
                .average().orElse(0);
    }

    private double getAverageDouble(List<Double> list) {
        return list.stream()
                .mapToDouble(a -> a)
                .average().orElse(0);
    }
}
