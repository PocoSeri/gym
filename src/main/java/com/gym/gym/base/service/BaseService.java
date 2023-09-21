package com.gym.gym.base.service;

import com.gym.gym.base.model.BaseModel;
import com.gym.gym.base.repository.BaseRepository;
import com.gym.gym.exception.AppException;
import com.gym.gym.utils.FetchOptions;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public abstract class BaseService<ENTITY extends BaseModel<PRIMARY_KEY>, PRIMARY_KEY extends Serializable> implements IBaseService<ENTITY , PRIMARY_KEY>{
    private final BaseRepository<ENTITY, PRIMARY_KEY> repository;
    public BaseService(BaseRepository<ENTITY, PRIMARY_KEY> repository) {
        this.repository = repository;
    }

    @Override
    public ENTITY insert(ENTITY entity) throws AppException {
        if (Objects.isNull(entity))
            throw new AppException(AppException.ErrCode.BAD_INPUT, "You can't save a null entity");
        if (existsById(entity.getId()))
            throw new AppException(AppException.ErrCode.ALREADY_EXISTS, String.format("The id: %s already exists", entity.getId()));
        return repository.save(entity);
    }

    public List<ENTITY> getList(FetchOptions fetchOptions) {

    }

    public boolean existsById(PRIMARY_KEY primaryKey) {
        return repository.existsById(primaryKey);
    }
}
