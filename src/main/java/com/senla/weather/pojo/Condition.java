package com.senla.weather.pojo;

import com.fasterxml.jackson.annotation.*;
import jakarta.annotation.Generated;

import javax.validation.Valid;
import java.util.LinkedHashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"text", "icon", "code"})
@Generated("jsonschema2pojo")
public class Condition {

    @JsonProperty("text")
    public String text;
    @JsonProperty("icon")
    public String icon;
    @JsonProperty("code")
    public int code;
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