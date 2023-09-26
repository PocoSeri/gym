package com.gym.gym.base.utils.annotation;

import com.gym.gym.base.utils.ControllerMethod;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface ControllerAllowedMethods {
    ControllerMethod[] httpMethod() default {ControllerMethod.ALL};
}
