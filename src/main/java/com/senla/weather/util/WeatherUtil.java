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

    /**
     * Converts a Weather object to a WeatherResponse object (DTO).
     *
     * @param weather the Weather object to be converted
     * @return a WeatherResponse object representing the converted Weather data
     */
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

    /**
     * Retrieves the most common weather condition from a list of weather conditions.
     *
     * @param weatherConditionList the list of weather conditions
     * @return the most common weather condition as a string
     * @throws RuntimeException if the most common weather condition cannot be determined
     */
    public static String getMostCommonWeatherCondition(List<String> weatherConditionList){
        Map<String, Long> map= weatherConditionList.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        Optional<String> mostCommon = map.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey);
        log.info("most common value has been found");
        return mostCommon.orElseThrow(() -> new RuntimeException("cant get most common weather condition"));
    }

    /**
     * Calculates the average value of a list of integers.
     *
     * @param list the list of integers
     * @return the average value as an integer
     */
    public static int getAverageInteger(List<Integer> list) {
        log.info("average value has been calculated");
        return (int) list.stream()
                .mapToInt(a -> a)
                .average().orElse(0);
    }

    /**
     * Calculates the average value of a list of doubles.
     *
     * @param list the list of doubles
     * @return the average value as a double
     */
    public static double getAverageDouble(List<Double> list) {
        log.info("average value has been calculated");
        return list.stream()
                .mapToDouble(a -> a)
                .average().orElse(0);
    }
}
