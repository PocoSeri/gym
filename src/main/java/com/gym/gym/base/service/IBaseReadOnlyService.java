package com.gym.gym.base.service;

import com.gym.gym.base.model.BaseModel;
import com.gym.gym.exception.AppException;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface IBaseReadOnlyService<ENTITY extends BaseModel<PRIMARY_KEY>, PRIMARY_KEY extends Serializable> {
    Optional<ENTITY> findById(PRIMARY_KEY id) throws AppException;

    //todo:implentare filtri per lista in base al parametro?
    List<ENTITY> getList() throws AppException;

    boolean existsById(PRIMARY_KEY id) throws AppException;
}
