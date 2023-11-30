package com.senla.weather.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestWeather {
    private Date startDate;
    private Date endDate;
}
