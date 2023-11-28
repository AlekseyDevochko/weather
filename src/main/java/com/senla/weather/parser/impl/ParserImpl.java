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


    private ObjectMapper objectMapper;
    @Override
    public WeatherFromAPI parse(Response response) {


        WeatherFromAPI weatherFromAPI = null;

        try {
            weatherFromAPI = objectMapper.readValue(response.getResponseBody(), WeatherFromAPI.class);

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        log.info("parse json to weatherFromAPI");
        return weatherFromAPI;
    }

    @Autowired
    public void setObjectMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }
}
