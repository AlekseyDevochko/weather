package com.senla.weather.pojo;

import java.util.LinkedHashMap;
import java.util.Map;


import com.fasterxml.jackson.annotation.*;
import jakarta.annotation.Generated;

import javax.validation.Valid;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "location",
        "current"
})
@Generated("jsonschema2pojo")
public class WeatherFromAPI {

    @JsonProperty("location")
    @Valid
    public Location location;
    @JsonProperty("current")
    @Valid
    public Current current;
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