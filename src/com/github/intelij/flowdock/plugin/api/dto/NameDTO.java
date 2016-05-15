package com.github.intelij.flowdock.plugin.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NameDTO extends BaseDTO {

    private String name;

    @JsonProperty("parameterized_name")
    private String parameterizedName;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParameterizedName() {
        return parameterizedName;
    }

    public void setParameterizedName(String parameterizedName) {
        this.parameterizedName = parameterizedName;
    }
}
