package com.gym.gym.base.mapper;

public interface IBaseMapper<Entity, Dto, PatchDto> extends IBaseReadOnlyMapper<Entity, Dto> {
    Entity fromPatchDtoToEntity(PatchDto entity);
}
