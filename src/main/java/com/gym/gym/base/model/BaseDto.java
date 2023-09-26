package com.gym.gym.base.model;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public abstract class BaseDto {

    private LocalDateTime registeredAt;
    private LocalDateTime modifiedAt;

    protected BaseDto(final BaseDtoBuilder<?, ?> b) {
    }

    public abstract static class BaseDtoBuilder<C extends BaseDto, B extends BaseDtoBuilder<C, B>> {
        public BaseDtoBuilder() {
        }

        protected abstract B self();

        public abstract C build();

        public String toString() {
            return "BaseDto.BaseDtoBuilder()";
        }
    }
}
