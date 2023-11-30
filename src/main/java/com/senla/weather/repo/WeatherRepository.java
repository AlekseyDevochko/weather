package com.senla.weather.repo;

import com.senla.weather.model.Weather;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;

import java.util.List;

@Repository
public interface WeatherRepository extends CrudRepository<Weather, Long> {

    @Query("select w from Weather w where w.date between :startDate and :endDate")
    List<Weather> getWeatherFromStartToEnd(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

    @Query("select w from Weather w order by w.id desc limit 1")
    Weather getLatestCreatedWeatherData();
}
