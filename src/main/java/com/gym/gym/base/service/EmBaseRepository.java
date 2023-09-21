package com.gym.gym.base.service;

import com.gym.gym.base.model.BaseModel;
import com.querydsl.core.BooleanBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.Optional;

public interface EmBaseRepository<T extends BaseModel<K>, K extends Serializable> {

    default Optional<Page<K>> getKeyPageOpt(BooleanBuilder builder, Pageable pageable) {
        return Optional.empty();
    }
}
