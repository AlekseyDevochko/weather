package com.senla.weather.service.impl;

import com.senla.weather.parser.Parser;
import com.senla.weather.pojo.WeatherFromAPI;
import com.senla.weather.service.ScheduleService;
import com.senla.weather.service.WeatherService;
import lombok.extern.slf4j.Slf4j;
import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.DefaultAsyncHttpClient;
import org.asynchttpclient.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

@Service
@Slf4j
public class ScheduleServiceImpl implements ScheduleService {


    private Parser parser;


    private WeatherService weatherService;



    @Scheduled(fixedRate = 3000)
    @Async
    public void getWeatherFromAPI() {
        AsyncHttpClient client = new DefaultAsyncHttpClient();
        try {
            Response response = client.prepareGet("https://weatherapi-com.p.rapidapi.com/current.json?q=Minsk")
                    .setHeader("X-RapidAPI-Key", "96d0d06362mshd98a51bafdd2f2ep1d7b84jsnb85e41b48c91")
                    .setHeader("X-RapidAPI-Host", "weatherapi-com.p.rapidapi.com")
                    .execute()
                    .toCompletableFuture()
                    .get();
            WeatherFromAPI weatherFromAPI = parser.parse(response);
            weatherService.create(weatherFromAPI);
            log.info("weather from API have been added");
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                client.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Autowired
    public void setParser(Parser parser) {
        this.parser = parser;
    }

    @Autowired
    public void setWeatherService(WeatherService weatherService) {
        this.weatherService = weatherService;
    }
}
