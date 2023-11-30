package com.senla.weather.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.Time;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WeatherResponse {
    private Integer temperature;
    private Double mph;
    private Integer pressure;
    private Integer humidity;
    private String weatherCondition;
    private String location;
    private Date date;
    private Time time;
}
