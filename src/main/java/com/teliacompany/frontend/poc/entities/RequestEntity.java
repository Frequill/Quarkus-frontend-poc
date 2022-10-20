package com.teliacompany.frontend.poc.entities;

import javax.json.bind.annotation.JsonbProperty;

public class RequestEntity {

    @JsonbProperty("requestId")
    private String requestId;
    @JsonbProperty("name")
    private String name;
    @JsonbProperty("jsonTest")
    private String jsonTest;

    public RequestEntity(String requestId, String name, String jsonTest) {
        this.requestId = requestId;
        this.name = name;
        this.jsonTest = jsonTest;
    }

    public RequestEntity() {}

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJsonTest() {
        return jsonTest;
    }

    public void setJsonTest(String jsonTest) {
        this.jsonTest = jsonTest;
    }
}