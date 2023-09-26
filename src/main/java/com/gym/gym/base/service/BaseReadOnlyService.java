package com.gym.gym.base.service;

import com.gym.gym.base.model.BaseModel;
import com.gym.gym.base.model.restresponse.PaginatedResponse;
import com.gym.gym.base.repository.BaseRepository;
import com.gym.gym.base.repository.FilterableRepository;
import com.gym.gym.base.repository.FilteringFactory;
import com.gym.gym.base.utils.ApplicationContextProvider;
import com.gym.gym.exception.AppException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
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
    protected final FilterableRepository<ENTITY> filterableRepository;

    protected final Class<?> documentService;
    protected final Class<?> documentClass;


    @Autowired
    public BaseReadOnlyService(final BaseRepository<ENTITY, PRIMARY_KEY> repository, FilterableRepository<ENTITY> filterableRepository) {

        this.documentService = this.getClass();
        // Automate mapping based on the service class name
        String documentClassName = documentService.getSimpleName().replace("Service", "");
        ApplicationContext context = ApplicationContextProvider.getApplicationContext();
        this.documentClass = context.getBean(documentClassName.toLowerCase()).getClass();
        this.repository = repository;
        this.filterableRepository = filterableRepository;
    }

    public Optional<ENTITY> findById(PRIMARY_KEY id){
        throwErrorIfIdNotExists(id);
        return repository.findById(id);
    }
    public Page<ENTITY> getList(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public PaginatedResponse<ENTITY> getList(Pageable pageable, List<String> filters) {
        Page<ENTITY> all = filterableRepository.findAllWithFilter((Class<ENTITY>) getModelClass(), FilteringFactory.parseFromParams(filters, getModelClass()), pageable);
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

    // Controllo l'istanza del service chiamato e ritorno la classe del model equivalente
    private Class<?> getModelClass() {
        return documentClass;
    }

}
