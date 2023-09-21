package com.gym.gym.base.service;

import com.gym.gym.base.model.BaseModel;
import com.gym.gym.base.repository.BaseRepository;
import com.gym.gym.exception.AppException;
import com.gym.gym.utils.FetchOptions;

import java.io.Serializable;
import java.util.List;

public abstract class BaseReadOnlyService<ENTITY extends BaseModel<PRIMARY_KEY>, PRIMARY_KEY extends Serializable> implements IBaseReadOnlyService<ENTITY, PRIMARY_KEY> {
    private final BaseRepository<ENTITY, PRIMARY_KEY> baseRepository;

    public BaseReadOnlyService(BaseRepository baseRepository) {
        this.baseRepository = baseRepository;
    }

    public ENTITY getOne(PRIMARY_KEY id) {
        return baseRepository.findById(id).orElseThrow(() ->
                new AppException(AppException.ErrCode.NOT_FOUND, String.format("Entity with id: %s not found", id)));
    }

    public List<ENTITY> getList(FetchOptions fetchOptions) {
        baseRepository.findAll()
    }
}
