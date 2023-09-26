package com.gym.gym.enums;

import com.gym.gym.base.model.enumUtils.BaseIdCodeDescriptionEnum;
import lombok.Getter;

@Getter
public enum Roles implements BaseIdCodeDescriptionEnum {

    ADMIN(1L, "Admin"),
    BASIC_CUSTOMER(2L, "Basic Customer"),
    EXTERNAL_WORKER(3L, "External Worker");

    private final Long id;
    private final String description;

    Roles(Long id, String description) {
        this.id = id;
        this.description = description;
    }
}
