package com.senla.weather.pojo;

import com.fasterxml.jackson.annotation.*;
import jakarta.annotation.Generated;

import javax.validation.Valid;
import java.util.LinkedHashMap;
import java.util.Map;


@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "last_updated_epoch",
        "last_updated",
        "temp_c",
        "temp_f",
        "is_day",
        "condition",
        "wind_mph",
        "wind_kph",
        "wind_degree",
        "wind_dir",
        "pressure_mb",
        "pressure_in",
        "precip_mm",
        "precip_in",
        "humidity",
        "cloud",
        "feelslike_c",
        "feelslike_f",
        "vis_km",
        "vis_miles",
        "uv",
        "gust_mph",
        "gust_kph"
})
@Generated("jsonschema2pojo")
public class Current {

    @JsonProperty("last_updated_epoch")
    public int lastUpdatedEpoch;
    @JsonProperty("last_updated")
    public String lastUpdated;
    @JsonProperty("temp_c")
    public int tempC;
    @JsonProperty("temp_f")
    public int tempF;
    @JsonProperty("is_day")
    public int isDay;
    @JsonProperty("condition")
    @Valid
    public Condition condition;
    @JsonProperty("wind_mph")
    public double windMph;
    @JsonProperty("wind_kph")
    public double windKph;
    @JsonProperty("wind_degree")
    public int windDegree;
    @JsonProperty("wind_dir")
    public String windDir;
    @JsonProperty("pressure_mb")
    public int pressureMb;
    @JsonProperty("pressure_in")
    public double pressureIn;
    @JsonProperty("precip_mm")
    public double precipMm;
    @JsonProperty("precip_in")
    public int precipIn;
    @JsonProperty("humidity")
    public int humidity;
    @JsonProperty("cloud")
    public int cloud;
    @JsonProperty("feelslike_c")
    public double feelslikeC;
    @JsonProperty("feelslike_f")
    public double feelslikeF;
    @JsonProperty("vis_km")
    public int visKm;
    @JsonProperty("vis_miles")
    public int visMiles;
    @JsonProperty("uv")
    public int uv;
    @JsonProperty("gust_mph")
    public double gustMph;
    @JsonProperty("gust_kph")
    public double gustKph;
    @JsonIgnore
    @Valid
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}