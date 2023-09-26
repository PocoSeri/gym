package com.gym.gym.mapping;

import com.gym.gym.base.mapper.BaseMapper;
import com.gym.gym.base.mapper.IBaseMapper;
import com.gym.gym.entity.Activity;
import com.gym.gym.model.ActivityDto;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.springframework.transaction.annotation.Transactional;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public abstract class ActivityMapper extends BaseMapper implements IBaseMapper<Activity, ActivityDto, ActivityDto> {

    @Override
    @Transactional
    @Named(value = "toEntity")
    public abstract Activity toEntity(ActivityDto dto);

    @Override
    public abstract ActivityDto toDto(Activity entity);

}
