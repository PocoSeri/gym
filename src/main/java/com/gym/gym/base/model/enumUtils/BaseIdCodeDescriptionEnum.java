package com.gym.gym.base.model.enumUtils;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;
import java.util.Objects;

public interface BaseIdCodeDescriptionEnum extends BaseEnum {
    Long getId();

    default String getCode() {
        return this.getClass().isAnnotationPresent(JsonProperty.class) ? ((JsonProperty)this.getClass().getAnnotation(JsonProperty.class)).value() : this.name();
    }

    static <T extends BaseIdCodeDescriptionEnum> T findById(Class<T> enumeration, Long id) {
        return (T) Arrays.stream((BaseIdCodeDescriptionEnum[])enumeration.getEnumConstants()).filter((v) -> {
            return Objects.equals(v.getId(), id);
        }).findFirst().orElse((BaseIdCodeDescriptionEnum) null);
    }

    static <T extends BaseIdCodeDescriptionEnum> T findByCode(Class<T> enumeration, String code) {
        return (T) Arrays.stream((BaseIdCodeDescriptionEnum[])enumeration.getEnumConstants()).filter((v) -> {
            return Objects.equals(v.getCode(), code);
        }).findFirst().orElse((BaseIdCodeDescriptionEnum) null);
    }
}
