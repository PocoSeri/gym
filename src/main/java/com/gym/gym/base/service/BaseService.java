package com.gym.gym.base.service;

import com.gym.gym.base.model.BaseModel;
import com.gym.gym.base.repository.BaseRepository;
import com.gym.gym.exception.AppException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Objects;

import static com.gym.gym.exception.AppException.ErrCode.UNPROCESSABLE_ENTITY;

@Service
public abstract class BaseService<ENTITY extends BaseModel<PRIMARY_KEY>, PRIMARY_KEY extends Serializable> extends BaseReadOnlyService<ENTITY, PRIMARY_KEY> implements IBaseService<ENTITY , PRIMARY_KEY>{

    protected Logger logger = LoggerFactory.getLogger(this.getClass());
    public BaseService(BaseRepository<ENTITY, PRIMARY_KEY> repository) {
        super(repository);
    }

    @Override
    public ENTITY insert(ENTITY entity) throws AppException {
        if (Objects.isNull(entity))
            throw new AppException(AppException.ErrCode.BAD_INPUT, "You can't save a null entity");
        if (entity.getId() != null && existsById(entity.getId()))
            throw new AppException(AppException.ErrCode.ALREADY_EXISTS, String.format("The id: %s already exists", entity.getId()));
        return repository.save(entity);
    }

    @Override
    public ENTITY update(ENTITY patchEntity) throws AppException {
        findById(patchEntity.getId()).orElseThrow(() -> {
            throw this.getIdNotExistException(patchEntity.getId());
        });


        return this.repository.save(patchEntity);
    }


    public List<ENTITY> getList() {
        return null;
    }

    public boolean existsById(PRIMARY_KEY primaryKey) {
        return repository.existsById(primaryKey);
    }


}
