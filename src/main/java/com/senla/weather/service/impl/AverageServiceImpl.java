package com.senla.weather.service.impl;

import com.senla.weather.model.Average;
import com.senla.weather.model.Weather;
import com.senla.weather.service.AverageService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class AverageServiceImpl implements AverageService {


    @Override
    public Average getAverage(List<Weather> weatherList) {
            Average average = new Average();

            average.setAverageTemperature(getAverageInteger(weatherList.stream().map(Weather::getTemperature).toList()));
            average.setAveragePressure(getAverageInteger(weatherList.stream().map(Weather::getPressure).toList()));
            average.setAverageHumidity(getAverageInteger(weatherList.stream().map(Weather::getHumidity).toList()));
            average.setAverageMph(getAverageDouble(weatherList.stream().map(Weather::getMph).toList()));
            average.setAverageWeatherCondition(getMostCommonWeatherCondition(weatherList.stream().map(Weather::getWeatherCondition).toList()));
            return average;
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
