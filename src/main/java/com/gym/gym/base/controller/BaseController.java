package com.gym.gym.base.controller;

import com.gym.gym.base.mapper.IBaseMapper;
import com.gym.gym.base.model.BaseDto;
import com.gym.gym.base.model.BaseIdDto;
import com.gym.gym.base.model.BaseModel;
import com.gym.gym.base.model.restresponse.RestResponse;
import com.gym.gym.base.service.IBaseService;
import com.gym.gym.exception.AppException;
import com.gym.gym.utils.ControllerMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;

import static com.gym.gym.exception.AppException.ErrCode.DATA_INTEGRITY;

public abstract class BaseController<ENTITY extends BaseModel<PRIMARY_KEY>, DTO extends BaseIdDto<PRIMARY_KEY>, PRIMARY_KEY extends Serializable, PATCH_DTO extends BaseDto> extends BaseReadOnlyController<ENTITY, DTO, PRIMARY_KEY> {
    protected Logger logger = LoggerFactory.getLogger(this.getClass());
    protected final IBaseService<ENTITY, PRIMARY_KEY> service;
    protected final IBaseMapper<ENTITY, DTO, PATCH_DTO> patchMapper;

    protected BaseController(IBaseService<ENTITY, PRIMARY_KEY> service, IBaseMapper<ENTITY, DTO, PATCH_DTO> mapper) {
        super(service, mapper);
        this.patchMapper = mapper;
        this.service = service;
    }

    @PostMapping
    @ResponseBody
    public RestResponse<DTO> insert(@RequestBody DTO dto) throws AppException {
        this.isAllowed(ControllerMethod.INSERT);
        ENTITY entity = this.mapper.toEntity(dto);
        ENTITY savedEntity = this.service.insert(entity);
        ENTITY refreshedEntity = this.service.findById(savedEntity.getId()).orElseThrow(() ->
                new AppException(DATA_INTEGRITY, "Something is wrong, impossible to retrieve the record just saved"));
        DTO insertedDto = this.mapper.toDto(refreshedEntity);
        return new RestResponse<>(insertedDto);
    }

    @PutMapping(
            path = {"/{id}"}
    )
    @ResponseBody
    public RestResponse<DTO> update(@PathVariable PRIMARY_KEY id, @RequestBody DTO dto) throws AppException {
        this.isAllowed(ControllerMethod.UPDATE);
        ENTITY entity = this.mapper.toEntity(dto);
        entity.setId(id);
        ENTITY savedEntity = this.service.update(entity);
        ENTITY refreshedEntity = this.service.findById(savedEntity.getId()).orElseThrow(() ->
                new AppException(DATA_INTEGRITY, "Something is wrong, impossible to retrieve the record just updated"));
        DTO updatedDto = this.mapper.toDto(refreshedEntity);
        return new RestResponse<>(updatedDto);
    }

    @PatchMapping(
            path = {"/{id}"}
    )
    @ResponseBody
    public RestResponse<DTO> patch(@PathVariable PRIMARY_KEY id, @RequestBody PATCH_DTO dto) throws AppException {
        this.isAllowed(ControllerMethod.PATCH);
        ENTITY entity = this.patchMapper.fromPatchDtoToEntity(dto);
        entity.setId(id);
        ENTITY savedEntity = this.service.patch(entity);
        ENTITY refreshedEntity = this.service.findById(savedEntity.getId()).orElseThrow(() ->
                new AppException(DATA_INTEGRITY, "Something is wrong, impossible to retrieve the record just updated"));
        DTO updatedDto = this.mapper.toDto(refreshedEntity);
        return new RestResponse<>(updatedDto);
    }

    @DeleteMapping(
            path = {"/{id}"}
    )
    @ResponseBody
    public RestResponse<String> delete(@PathVariable PRIMARY_KEY id) throws AppException {
        this.isAllowed(ControllerMethod.DELETE);
        this.service.delete(id);
        return new RestResponse<>();
    }
}
