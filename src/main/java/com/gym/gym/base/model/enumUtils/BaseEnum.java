package com.gym.gym.base.model.enumUtils;

public interface BaseEnum {
    default String getDescription() {
        return this.name();
    }

    String name();
}
