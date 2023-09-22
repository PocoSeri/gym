package com.gym.gym.base.mapper;


import com.gym.gym.base.model.DropdownDto;
import org.mapstruct.IterableMapping;
import org.mapstruct.Named;

import java.util.ArrayList;
import java.util.List;

public interface IBaseReadOnlyMapper<ENTITY, DTO> {
    @Named("toEntity")
    ENTITY toEntity(DTO dto);

    DTO toDto(ENTITY entity);

    List<DTO> entityToDtoList(List<ENTITY> entity);

    @IterableMapping(
            qualifiedByName = {"toEntity"}
    )
    List<ENTITY> dtoToEntityList(List<DTO> entity);

    default List<DropdownDto> entityToDropdownDtoList(List<ENTITY> entity) {
        return new ArrayList<>();
    }
}
