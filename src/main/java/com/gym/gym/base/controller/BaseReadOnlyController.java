package com.gym.gym.base.controller;

import com.gym.gym.base.mapper.IBaseReadOnlyMapper;
import com.gym.gym.base.model.BaseDto;
import com.gym.gym.base.model.BaseModel;
import com.gym.gym.base.model.DropdownDto;
import com.gym.gym.base.model.restresponse.PaginatedResponse;
import com.gym.gym.base.model.restresponse.RestResponse;
import com.gym.gym.base.service.IBaseReadOnlyService;
import com.gym.gym.exception.AppException;
import com.gym.gym.utils.BaseConstants;
import com.gym.gym.utils.ControllerMethod;
import com.gym.gym.utils.annotation.ControllerAllowedMethods;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.MethodNotAllowedException;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

import static com.gym.gym.exception.AppException.ErrCode.NOT_FOUND;
import static com.gym.gym.utils.ControllerMethod.DROPDOWN;
import static com.gym.gym.utils.ControllerMethod.GET_ONE;

public abstract class BaseReadOnlyController<ENTITY extends BaseModel<PRIMARY_KEY>, DTO extends BaseDto, PRIMARY_KEY extends Serializable> {
    protected final IBaseReadOnlyService<ENTITY, PRIMARY_KEY> readonlyService;
    protected final IBaseReadOnlyMapper<ENTITY, DTO> mapper;


    private static final Logger logger = LoggerFactory.getLogger(BaseReadOnlyController.class);

    @GetMapping(
            path = {"/{id}"}
    )
    @ResponseBody
    public RestResponse<DTO> getOne(@PathVariable PRIMARY_KEY id, HttpServletRequest request) throws AppException {
        this.isAllowed(GET_ONE);
        Optional<ENTITY> entity = this.readonlyService.findById(id);
        IBaseReadOnlyMapper<ENTITY, DTO> var10001 = this.mapper;
        Objects.requireNonNull(var10001);
        DTO dto = (DTO) entity.map(var10001::toDto).orElseThrow(() ->
                new AppException(NOT_FOUND, "Could not find id " + id));
        return new RestResponse<>(dto);
    }

    @GetMapping
    @ResponseBody
    public PaginatedResponse<ENTITY> getList(@RequestParam int page, @RequestParam int size, @RequestParam(required = false) List<String> filter) {
        Pageable pageable = PageRequest.of(page, size);
        return readonlyService.getList(pageable, filter);
    }

    @GetMapping({"/dropdown"})
    @ResponseBody
    public RestResponse<List<DropdownDto>> dropdown(Pageable page, HttpServletRequest request, HttpServletResponse response) throws AppException {
        this.isAllowed(DROPDOWN);
        Page<ENTITY> res = this.readonlyService.getList(page);
        response.setHeader(BaseConstants.X_TOTAL_COUNT, Integer.toString(page.getPageNumber()));
        List<DropdownDto> dtoList = this.mapper.entityToDropdownDtoList(res.getContent());
        return new RestResponse<>(dtoList);
    }

    protected void isAllowed(ControllerMethod method) {
        if (this.getClass().isAnnotationPresent(ControllerAllowedMethods.class)) {
            List<HttpMethod> list = new ArrayList<>();
            Set<ControllerMethod> collect = (Set)Arrays.stream(((ControllerAllowedMethods)this.getClass().getAnnotation(ControllerAllowedMethods.class)).httpMethod()).collect(Collectors.toSet());
            if (!collect.contains(method) && !collect.contains(ControllerMethod.ALL)) {
                Arrays.stream(((ControllerAllowedMethods)this.getClass().getAnnotation(ControllerAllowedMethods.class)).httpMethod()).forEach((x) -> {
                    list.add(x.getHttpMethod());
                });
                throw new MethodNotAllowedException(method.getHttpMethod(), list);
            }
        }
    }

    public BaseReadOnlyController(final IBaseReadOnlyService<ENTITY, PRIMARY_KEY> readonlyService, final IBaseReadOnlyMapper<ENTITY, DTO> mapper) {
        this.readonlyService = readonlyService;
        this.mapper = mapper;
    }
}
