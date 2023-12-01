package com.senla.weather.service.impl;

import com.senla.weather.model.Weather;
import com.senla.weather.pojo.WeatherFromAPI;
import com.senla.weather.repo.WeatherRepository;
import com.senla.weather.response.AverageWeatherResponse;
import com.senla.weather.response.WeatherResponse;
import com.senla.weather.service.WeatherService;
import com.senla.weather.util.WeatherUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class WeatherServiceImpl implements WeatherService {

    public static final int SECONDS_MULTIPLIER = 1000;
    private final WeatherRepository weatherRepository;

    /**
     * Creates a new weather entry based on the data from WeatherFromAPI.
     *
     * @param weatherFromAPI the WeatherFromAPI object containing the weather data
     */
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
        weather.setDate(new Date(weatherFromAPI.location.localtimeEpoch* SECONDS_MULTIPLIER));
        weatherRepository.save(weather);
        log.info("new weather created");
    }

    /**
     * Retrieves the weather data for a specified period of time.
     *
     * @param startDate the start date of the period
     * @param endDate the end date of the period
     * @return a list of Weather objects representing the weather data within the specified period
     */
    @Override
    public List<Weather> getPeriodTimeWeather(Date startDate, Date endDate) {
        List<Weather> weatherList = weatherRepository.getWeatherFromStartToEnd(startDate, endDate);
        log.info("start and end dates weather list got");
        return weatherList;
    }

    /**
     * Method to retrieve the latest weather data.
     *
     * @return a WeatherResponse object containing the latest weather data
     */
    @Override
    public WeatherResponse getLatestWeatherData() {
        Weather weather = weatherRepository.getLatestCreatedWeatherData();
        log.info("get current weather data");
        return WeatherUtil.toDTO(weather);
    }


    /**
     * Method to calculate the average weather based on a list of weather data.
     *
     * @param weatherList the list of Weather objects containing weather data
     * @return an AverageWeatherResponse object with the average weather data
     */
    @Override
    public AverageWeatherResponse getAverage(List<Weather> weatherList) {
        AverageWeatherResponse averageWeatherResponse = new AverageWeatherResponse();
        averageWeatherResponse.setAverageTemperature(WeatherUtil.getAverageInteger(weatherList.stream().map(Weather::getTemperature).toList()));
        averageWeatherResponse.setAveragePressure(WeatherUtil.getAverageInteger(weatherList.stream().map(Weather::getPressure).toList()));
        averageWeatherResponse.setAverageHumidity(WeatherUtil.getAverageInteger(weatherList.stream().map(Weather::getHumidity).toList()));
        averageWeatherResponse.setAverageMph(WeatherUtil.getAverageDouble(weatherList.stream().map(Weather::getMph).toList()));
        averageWeatherResponse.setAverageWeatherCondition(WeatherUtil.getMostCommonWeatherCondition(weatherList.stream().map(Weather::getWeatherCondition).toList()));
        log.info("get average");
        return averageWeatherResponse;
    }
}
