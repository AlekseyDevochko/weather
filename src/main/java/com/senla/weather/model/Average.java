package com.senla.weather.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Average {
    private Integer averageTemperature;
    private Double averageMph;
    private Integer averagePressure;
    private Integer averageHumidity;
    private String averageWeatherCondition;
}
