package com.gym.gym.base.mapper;


import com.gym.gym.base.model.DropdownDto;
import org.mapstruct.IterableMapping;
import org.mapstruct.Named;

import java.util.ArrayList;
import java.util.List;

public interface IBaseReadOnlyMapper<Entity, Dto> {
    @Named("toEntity")
    Entity toEntity(Dto dto);

    Dto toDto(Entity entity);

    List<Dto> entityToDtoList(List<Entity> entity);

    @IterableMapping(
            qualifiedByName = {"toEntity"}
    )
    List<Entity> dtoToEntityList(List<Dto> entity);

    default List<DropdownDto> entityToDropdownDtoList(List<Entity> entity) {
        return new ArrayList();
    }
}
