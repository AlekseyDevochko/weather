package com.senla.weather.service;

import com.senla.weather.model.Weather;
import com.senla.weather.repo.WeatherRepository;
import com.senla.weather.response.WeatherResponse;
import com.senla.weather.service.impl.WeatherServiceImpl;
import com.senla.weather.util.WeatherUtil;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Date;
import java.sql.Time;

import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class WeatherServiceTest {
    @Mock
    private WeatherRepository weatherRepository;

    @InjectMocks
    private WeatherServiceImpl weatherService;

    @Test
    public void getLatestWeatherData_ReturnSuccess(){
        Weather weather = new Weather(2L,-4, 27.0, 850, 68, "Cloudy", "Minsk", new Date(1701383975L), new Time(1701383961L));
        given(weatherRepository.getLatestCreatedWeatherData()).willReturn(weather);
        WeatherResponse weatherResponse = new WeatherResponse(-4, 27.0, 850, 68, "Cloudy", "Minsk", new Date(1701383975L), new Time(1701383961L));
        Assertions.assertThat(weatherResponse).isEqualTo(WeatherUtil.toDTO(weather));
    }
}
