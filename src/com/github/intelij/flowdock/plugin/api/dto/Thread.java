package com.github.intelij.flowdock.plugin.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Thread extends BaseDTO {

    private String title;

    private String body;

    @JsonProperty("initial_message")
    private long initialMessage;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public long getInitialMessage() {
        return initialMessage;
    }

    public void setInitialMessage(long initialMessage) {
        this.initialMessage = initialMessage;
    }
}
