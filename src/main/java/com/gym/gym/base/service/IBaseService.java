package com.gym.gym.base.service;

import com.gym.gym.base.model.BaseModel;
import com.gym.gym.exception.AppException;

import java.io.Serializable;

public interface IBaseService<ENTITY extends BaseModel<PRIMARY_KEY>, PRIMARY_KEY extends Serializable> extends IBaseReadOnlyService<ENTITY, PRIMARY_KEY> {
    ENTITY insert(ENTITY entity) throws AppException;

    ENTITY update(ENTITY updateEntity) throws AppException;

    ENTITY patch(ENTITY patchEntity) throws AppException;

    void delete(PRIMARY_KEY id) throws AppException;
}
