package com.senla.weather.parser.impl;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.senla.weather.parser.Parser;
import com.senla.weather.pojo.WeatherFromAPI;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.asynchttpclient.Response;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class ParserImpl implements Parser {

    private final ObjectMapper objectMapper;

    @Override
    public WeatherFromAPI parse(Response response) throws JsonProcessingException {
        WeatherFromAPI weatherFromAPI = objectMapper.readValue(response.getResponseBody(), WeatherFromAPI.class);
        log.info("parse json to weatherFromAPI");
        return weatherFromAPI;
    }
}
