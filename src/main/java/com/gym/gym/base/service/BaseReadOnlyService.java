package com.gym.gym.base.service;

import com.gym.gym.base.model.BaseModel;
import com.gym.gym.base.repository.BaseRepository;
import com.gym.gym.exception.AppException;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static com.gym.gym.exception.AppException.ErrCode.ALREADY_EXISTS;
import static com.gym.gym.exception.AppException.ErrCode.NOT_FOUND;

public abstract class BaseReadOnlyService<ENTITY extends BaseModel<PRIMARY_KEY>, PRIMARY_KEY extends Serializable> implements IBaseReadOnlyService<ENTITY, PRIMARY_KEY> {

    protected final BaseRepository<ENTITY, PRIMARY_KEY> repository;

    public BaseReadOnlyService(final BaseRepository<ENTITY, PRIMARY_KEY> repository) {
        this.repository = repository;
    }

    public ENTITY getOne(PRIMARY_KEY id) {
        return findById(id).orElseThrow(() ->
                new AppException(NOT_FOUND, String.format("Entity with id: %s not found", id)));
    }

    public Optional<ENTITY> findById(PRIMARY_KEY id){

        return repository.findById(id);
    }
    public List<ENTITY> getAll() {
        return repository.findAll();
    }

    public void throwErrorIfIdExists(PRIMARY_KEY id) throws AppException {
        if (this.repository.existsById(id)) {
            throw new AppException(ALREADY_EXISTS, "Id " + id + " already exists");
        }
    }

    public AppException getIdNotExistException(PRIMARY_KEY id) {
        return new AppException(NOT_FOUND, "Could not find id " + id);
    }

    public AppException getIdsNotExistException(Set<PRIMARY_KEY> ids) {
        return new AppException(NOT_FOUND, "Could not find some of these IDs: " + ids.toString());
    }

}
