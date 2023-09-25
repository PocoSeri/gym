package com.gym.gym.base.service;

import com.gym.gym.base.model.BaseModel;
import com.gym.gym.base.model.restresponse.PaginatedResponse;
import com.gym.gym.base.repository.BaseRepository;
import com.gym.gym.base.repository.FilteringFactory;
import com.gym.gym.entity.Customer;
import com.gym.gym.exception.AppException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

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

    public Optional<ENTITY> findById(PRIMARY_KEY id){
        throwErrorIfIdNotExists(id);
        return repository.findById(id);
    }
    public Page<ENTITY> getList(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public PaginatedResponse<ENTITY> getList(Pageable pageable, List<String> filters) {
        Page<ENTITY> all = repository.findAllWithFilter((Class<ENTITY>) Customer.class, FilteringFactory.parseFromParams(filters, Customer.class), pageable);
        return PaginatedResponse.<ENTITY>builder()
                .currentPage(all.getNumber())
                .totalItems(all.getTotalElements())
                .totalPages(all.getTotalPages())
                .items(all.getContent())
                .hasNext(all.hasNext())
                .build();
    }

    public void throwErrorIfIdExists(PRIMARY_KEY id) throws AppException {
        if (this.repository.existsById(id)) {
            throw new AppException(ALREADY_EXISTS, "Id " + id + " already exists");
        }
    }
    public void throwErrorIfIdNotExists(PRIMARY_KEY id) throws AppException {
        if (!this.repository.existsById(id)) {
            throw this.getIdNotExistException(id);
        }
    }

    public AppException getIdNotExistException(PRIMARY_KEY id) {
        return new AppException(NOT_FOUND, "Could not find id " + id);
    }

    public AppException getIdsNotExistException(Set<PRIMARY_KEY> ids) {
        return new AppException(NOT_FOUND, "Could not find some of these IDs: " + ids.toString());
    }

}
