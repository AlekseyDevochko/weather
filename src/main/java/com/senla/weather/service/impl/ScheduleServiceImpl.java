package com.senla.weather.service.impl;

import com.senla.weather.parser.Parser;
import com.senla.weather.pojo.WeatherFromAPI;
import com.senla.weather.service.ScheduleService;
import com.senla.weather.service.WeatherService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.DefaultAsyncHttpClient;
import org.asynchttpclient.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

@Service
@Slf4j
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {

    private final Parser parser;
    private final WeatherService weatherService;

    @Value("${api-url}")
    private String url;
    @Value("${api-key}")
    private String apiKey;
    @Value("${api-key-value}")
    private String apiKeyValue;
    @Value("${api-host}")
    private String apiHost;
    @Value("${api-host-value}")
    private String apiHostValue;

    @Scheduled(cron = "*/5 * * * * *")
    @Async
    public void getWeatherFromAPI() throws ExecutionException, InterruptedException, IOException {
        AsyncHttpClient client = new DefaultAsyncHttpClient();
        Response response = client.prepareGet(url)
                .setHeader(apiKey, apiKeyValue)
                .setHeader(apiHost, apiHostValue)
                .execute()
                .toCompletableFuture()
                .get();
        WeatherFromAPI weatherFromAPI = parser.parse(response);
        weatherService.create(weatherFromAPI);
        log.info("weather from API has been added");
        client.close();
    }
}
