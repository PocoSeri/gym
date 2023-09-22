package com.gym.gym.base.mapper;

import org.mapstruct.BeforeMapping;
import org.mapstruct.TargetType;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class BaseMapper {
    public BaseMapper() {
    }

    @BeforeMapping
    public <T> Set<T> fixLazyLoadingSet(Collection<?> c, @TargetType Class<?> targetType) {
        return !MapperUtils.wasInitialized(c) ? Collections.emptySet() : null;
    }

    @BeforeMapping
    public <T> List<T> fixLazyLoadingList(Collection<?> c, @TargetType Class<?> targetType) {
        return !MapperUtils.wasInitialized(c) ? Collections.emptyList() : null;
    }
}
