package com.github.intelij.flowdock.plugin.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Flow extends NameDTO {

    private String url;

    @JsonProperty("web_url")
    private String webUrl;

    private Organization organization;

    private List<User> users;

    public List<User> getUsers() {
        return users;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getWebUrl() {
        return webUrl;
    }

    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }

}
