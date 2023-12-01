package com.senla.weather.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.senla.weather.model.Weather;
import com.senla.weather.request.RequestWeather;
import com.senla.weather.response.AverageWeatherResponse;
import com.senla.weather.response.WeatherResponse;
import com.senla.weather.service.WeatherService;
import com.senla.weather.util.WeatherUtil;
import lombok.SneakyThrows;
import org.assertj.core.api.Assertions;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@WebMvcTest(controllers = WeatherController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class WeatherControllerTest {

    @Autowired
    private  MockMvc mockMvc;
    @Autowired
    private  ObjectMapper objectMapper;

    private WeatherResponse weatherResponse;
    private AverageWeatherResponse averageWeatherResponse;
    private List<Weather> weatherList;
    private RequestWeather requestWeather;

    @MockBean
    private WeatherService weatherService;

    @BeforeEach
    public void init(){
        weatherResponse = new WeatherResponse();
        weatherResponse.setWeatherCondition("Cloudy");
        weatherResponse.setPressure(800);
        weatherList = List.of(new Weather(1L,-3, 23.0, 800, 65, "Cloudy", "Minsk", new Date(1701383961L), new Time(1701383961L)),
                new Weather(2L,-4, 27.0, 850, 68, "Cloudy", "Minsk", new Date(1701383975L), new Time(1701383961L)),
                new Weather(3L,-7, 22.0, 750, 62, "Rainy", "Minsk", new Date(1701383990L), new Time(1701383961L)),
                new Weather(4L,-6, 20.0, 700, 65, "Snow", "Minsk", new Date(1701384006L), new Time(1701383961L)));
        averageWeatherResponse = new AverageWeatherResponse(-5, 23.0, 750, 65, "Cloudy");
        requestWeather = new RequestWeather(new Date(1701383961L), new Date(1701384006L));
    }

    @Test
    @SneakyThrows
    public void getLatestWeatherDataTest_ReturnSuccess(){
        given(weatherService.getLatestWeatherData()).willReturn(weatherResponse);
        ResultActions response = mockMvc.perform(get("/weather/current"));
        response.andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.jsonPath("$.pressure", CoreMatchers.is(weatherResponse.getPressure())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.weatherCondition", CoreMatchers.is(weatherResponse.getWeatherCondition())));
    }

    @Test
    @SneakyThrows
    public void getAverageTest_ReturnSuccess(){
        given(weatherService.getPeriodTimeWeather(requestWeather.getStartDate(), requestWeather.getEndDate())).willReturn(weatherList);
        given(weatherService.getAverage(weatherList)).willReturn(averageWeatherResponse);
        Assertions.assertThat(averageWeatherResponse.getAverageMph()).isEqualTo(WeatherUtil.getAverageDouble(weatherList.stream().map(Weather::getMph).toList()));
    }



}
