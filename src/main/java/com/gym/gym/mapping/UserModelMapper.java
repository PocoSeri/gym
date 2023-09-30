package com.gym.gym.mapping;

import com.gym.gym.base.mapper.BaseMapper;
import com.gym.gym.base.mapper.IBaseMapper;
import com.gym.gym.entity.UserModel;
import com.gym.gym.model.UserDto;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.transaction.annotation.Transactional;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public abstract class UserModelMapper extends BaseMapper implements IBaseMapper<UserModel, UserDto, UserDto> {

    @Override
    @Transactional
    @Named(value = "toEntity")
    public abstract UserModel toEntity(UserDto dto);

    @Override
    @Mapping(target = "jwt", ignore = true)
    @Mapping(target = "registeredAt", ignore = true)
    @Mapping(target = "modifiedAt", ignore = true)
    public abstract UserDto toDto(UserModel entity);
}
