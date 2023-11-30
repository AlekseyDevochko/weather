package com.senla.weather.util;

import com.senla.weather.model.Weather;
import com.senla.weather.response.WeatherResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
@Slf4j
public class WeatherUtil {

    public static WeatherResponse toDTO(Weather weather){
        WeatherResponse weatherResponse = new WeatherResponse();
        weatherResponse.setTemperature(weather.getTemperature());
        weatherResponse.setMph(weather.getMph());
        weatherResponse.setPressure(weather.getPressure());
        weatherResponse.setHumidity(weather.getHumidity());
        weatherResponse.setWeatherCondition(weather.getWeatherCondition());
        weatherResponse.setLocation(weather.getLocation());
        weatherResponse.setDate(weather.getDate());
        weatherResponse.setTime(weather.getTime());
        log.info("dto has been created");
        return weatherResponse;
    }

    public static String getMostCommonWeatherCondition(List<String> weatherConditionList){
        Map<String, Long> map= weatherConditionList.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        Optional<String> mostCommon = map.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey);
        log.info("most common value has been found");
        return mostCommon.orElseThrow(() -> new RuntimeException("cant get most common weather condition"));
    }

    public static int getAverageInteger(List<Integer> list) {
        log.info("average value has been calculated");
        return (int) list.stream()
                .mapToInt(a -> a)
                .average().orElse(0);
    }

    public static double getAverageDouble(List<Double> list) {
        log.info("average value has been calculated");
        return list.stream()
                .mapToDouble(a -> a)
                .average().orElse(0);
    }
}
