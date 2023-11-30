package com.senla.weather.parser;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.senla.weather.pojo.WeatherFromAPI;
import org.asynchttpclient.Response;

public interface Parser {
    WeatherFromAPI parse(Response response) throws JsonProcessingException;
}
