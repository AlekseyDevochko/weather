package com.senla.weather.pojo;

import com.fasterxml.jackson.annotation.*;
import jakarta.annotation.Generated;

import javax.validation.Valid;
import java.util.LinkedHashMap;
import java.util.Map;


@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "name",
        "region",
        "country",
        "lat",
        "lon",
        "tz_id",
        "localtime_epoch",
        "localtime"
})
@Generated("jsonschema2pojo")
public class Location {

    @JsonProperty("name")
    public String name;
    @JsonProperty("region")
    public String region;
    @JsonProperty("country")
    public String country;
    @JsonProperty("lat")
    public double lat;
    @JsonProperty("lon")
    public double lon;
    @JsonProperty("tz_id")
    public String tzId;
    @JsonProperty("localtime_epoch")
    public long localtimeEpoch;
    @JsonProperty("localtime")
    public String localtime;
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