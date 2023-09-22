package com.gym.gym.base.model;

public abstract class BaseIdDto<K> extends BaseDto {
    public abstract K getId();

    public abstract void setId(K id);

    protected BaseIdDto(final BaseIdDtoBuilder<K, ?, ?> b) {
        super(b);
    }

    public BaseIdDto() {
    }

    public abstract static class BaseIdDtoBuilder<K, C extends BaseIdDto<K>, B extends BaseIdDtoBuilder<K, C, B>> extends BaseDto.BaseDtoBuilder<C, B> {
        public BaseIdDtoBuilder() {
        }

        protected abstract B self();

        public abstract C build();

        public String toString() {
            return "BaseIdDto.BaseIdDtoBuilder(super=" + super.toString() + ")";
        }
    }
}
