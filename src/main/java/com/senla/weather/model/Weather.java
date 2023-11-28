package com.senla.weather.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.sql.Date;


@Entity
@Table(name = "daily")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Weather {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer temperature;
    private Double mph;
    private Integer pressure;
    private Integer humidity;
    private String weatherCondition;
    private String location;
    @Temporal(TemporalType.DATE)
    private Date date;

}
