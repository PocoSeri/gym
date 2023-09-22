package com.gym.gym.base.model;

public abstract class BaseDto {
    protected BaseDto(final BaseDtoBuilder<?, ?> b) {
    }

    public BaseDto() {
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
