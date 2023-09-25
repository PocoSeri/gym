package com.gym.gym.base.service;

import com.gym.gym.base.model.BaseModel;
import com.gym.gym.base.model.restresponse.PaginatedResponse;
import com.gym.gym.exception.AppException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface IBaseReadOnlyService<ENTITY extends BaseModel<PRIMARY_KEY>, PRIMARY_KEY extends Serializable> {
    Optional<ENTITY> findById(PRIMARY_KEY id) throws AppException;

    Page<ENTITY> getList(Pageable pageable) throws AppException;

    PaginatedResponse<ENTITY> getList(Pageable pageable, List<String> filters) throws AppException;

    boolean existsById(PRIMARY_KEY id) throws AppException;
}
