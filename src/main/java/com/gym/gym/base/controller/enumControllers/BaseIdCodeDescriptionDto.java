package com.gym.gym.base.controller.enumControllers;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class BaseIdCodeDescriptionDto {
    private Long id;
    private String code;
    private String description;
}
