package com.senla.weather.parser;

import com.senla.weather.pojo.WeatherFromAPI;
import org.asynchttpclient.Response;

public interface Parser {
    WeatherFromAPI parse(Response weatherFromAPI);

}
