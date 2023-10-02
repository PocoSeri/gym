package com.gym.gym.security;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Objects;

@Component
public class BearerTokenRequestInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate requestTemplate){
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if(Objects.nonNull(requestAttributes)){

            String authorizationHeader = requestAttributes.getRequest().getHeader(HttpHeaders.AUTHORIZATION);
            String authorizationHeaderInSession =(String) requestAttributes.getRequest().getSession().getAttribute(HttpHeaders.AUTHORIZATION);

            if(authorizationHeader != null){
                requestTemplate.header(HttpHeaders.AUTHORIZATION);
                requestTemplate.header(HttpHeaders.AUTHORIZATION,authorizationHeader);
            }else if (authorizationHeaderInSession != null){
                requestTemplate.header(HttpHeaders.AUTHORIZATION);
                requestTemplate.header(HttpHeaders.AUTHORIZATION,authorizationHeaderInSession);
            }
        }
    }
}