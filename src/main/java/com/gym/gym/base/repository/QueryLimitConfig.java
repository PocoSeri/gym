package com.gym.gym.base.repository;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class QueryLimitConfig {
    @Value("${com.gym.query-max-limit}")
    private Integer queryMaxLimit;
    public static Integer QUERY_MAX_LIMIT;

    public QueryLimitConfig() {
    }

    @Value("${com.gym.query-max-limit}")
    public void setNameStatic(Integer queryMaxLimit) {
        QUERY_MAX_LIMIT = queryMaxLimit;
    }
}
