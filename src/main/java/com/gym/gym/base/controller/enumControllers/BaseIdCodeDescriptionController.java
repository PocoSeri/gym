package com.gym.gym.base.controller.enumControllers;

import com.gym.gym.base.model.enumUtils.BaseIdCodeDescriptionEnum;
import com.gym.gym.base.model.restresponse.RestResponse;
import com.gym.gym.exception.AppException;
import org.springframework.web.bind.annotation.GetMapping;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.gym.gym.exception.AppException.ErrCode.IMPLEMENTATION_MISSING;

public interface BaseIdCodeDescriptionController<ENUMERATION extends Enum<ENUMERATION> & BaseIdCodeDescriptionEnum> {
    @GetMapping
    default RestResponse<List<BaseIdCodeDescriptionDto>> getList() {
        Type baseIdCodeDescriptionControllerClass = (Type) Arrays.stream(this.getClass().getGenericInterfaces()).filter((z) -> {
            return z.getTypeName().split("<")[0].equals(BaseIdCodeDescriptionController.class.getTypeName());
        }).findFirst().orElseThrow(() -> {
            return new AppException(IMPLEMENTATION_MISSING);
        });
        ParameterizedType parameterizedType = (ParameterizedType)baseIdCodeDescriptionControllerClass;
        Class<? extends BaseIdCodeDescriptionEnum> baseIdCodeDescriptionEnumImplementation = (Class)parameterizedType.getActualTypeArguments()[0];
        return new RestResponse(this.toBaseIdCodeDescriptionDtoList(baseIdCodeDescriptionEnumImplementation));
    }

    default List<BaseIdCodeDescriptionDto> toBaseIdCodeDescriptionDtoList(Class<? extends BaseIdCodeDescriptionEnum> enumClass) {
        return (List)Arrays.stream((BaseIdCodeDescriptionEnum[])enumClass.getEnumConstants()).map((x) -> {
            return BaseIdCodeDescriptionDto.builder().description(x.getDescription()).code(x.getCode()).id(x.getId()).build();
        }).collect(Collectors.toList());
    }
}
