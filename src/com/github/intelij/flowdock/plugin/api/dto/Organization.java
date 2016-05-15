package com.github.intelij.flowdock.plugin.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Organization extends NameDTO {

    private boolean active;

    private String url;

    @JsonProperty("user_count")
    private int userCount;

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getUserCount() {
        return userCount;
    }

    public void setUserCount(int userCount) {
        this.userCount = userCount;
    }
}
