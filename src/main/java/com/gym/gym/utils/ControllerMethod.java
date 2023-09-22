package com.gym.gym.utils;

import org.springframework.http.HttpMethod;

public enum ControllerMethod {
    ALL,
    INSERT(HttpMethod.POST),
    UPDATE(HttpMethod.PUT),
    PATCH(HttpMethod.PATCH),
    DELETE(HttpMethod.DELETE),
    GET_ONE(HttpMethod.GET),
    GET_LIST(HttpMethod.GET),
    DROPDOWN(HttpMethod.GET);

    private HttpMethod httpMethod;

    private ControllerMethod(HttpMethod httpMethod) {
        this.httpMethod = httpMethod;
    }

    private ControllerMethod() {
    }

    public HttpMethod getHttpMethod() {
        return this.httpMethod;
    }
}
