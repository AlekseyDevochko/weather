package com.senla.weather.service;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

public interface ScheduleService{
    void getWeatherFromAPI() throws ExecutionException, InterruptedException, IOException;
}
