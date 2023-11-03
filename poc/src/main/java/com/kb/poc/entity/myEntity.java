package com.kb.poc.entity;

import org.springframework.stereotype.Component;

@Component
public class myEntity {
    public String getResponseString() {
        return responseString;
    }

    public void setResponseString(String responseString) {
        this.responseString = responseString;
    }

    String responseString;
}
