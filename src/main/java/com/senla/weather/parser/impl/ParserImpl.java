package com.senla.weather.parser.impl;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.senla.weather.parser.Parser;
import com.senla.weather.pojo.WeatherFromAPI;
import lombok.extern.slf4j.Slf4j;
import org.asynchttpclient.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ParserImpl implements Parser {

    @Autowired
    private ObjectMapper objectMapper;
    @Override
    public WeatherFromAPI parse(Response weatherFromAPI) {


        WeatherFromAPI weather = null;

        try {
            weather = objectMapper.readValue(weatherFromAPI.getResponseBody(), WeatherFromAPI.class);

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        log.error("HHh");
        return weather;
    }
}
