package com.gym.gym.mapping;

import com.gym.gym.base.mapper.BaseMapper;
import com.gym.gym.base.mapper.IBaseMapper;
import com.gym.gym.entity.Customer;
import com.gym.gym.model.CustomerDto;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.springframework.transaction.annotation.Transactional;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public abstract class CustomerMapper extends BaseMapper implements IBaseMapper<Customer, CustomerDto, CustomerDto> {

    @Override
    @Transactional
    @Named(value = "toEntity")
    public abstract Customer toEntity(CustomerDto dto);

    @Override
    public abstract CustomerDto toDto(Customer entity);

}
