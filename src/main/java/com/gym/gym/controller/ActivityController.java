package com.gym.gym.controller;

import com.gym.gym.base.controller.BaseController;
import com.gym.gym.base.utils.ControllerMethod;
import com.gym.gym.base.utils.annotation.ControllerAllowedMethods;
import com.gym.gym.entity.Activity;
import com.gym.gym.mapping.ActivityMapper;
import com.gym.gym.model.ActivityDto;
import com.gym.gym.service.ActivityService;
import com.gym.gym.swagger.OpenApiConfig;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ControllerAllowedMethods(httpMethod = {ControllerMethod.GET_ONE, ControllerMethod.GET_LIST, ControllerMethod.UPDATE, ControllerMethod.INSERT, ControllerMethod.DELETE})
@RequestMapping("/api/activities")
@SecurityRequirement(name = OpenApiConfig.SECURITY_SCHEMA_NAME)
@Tag(name = "Activity")
public class ActivityController extends BaseController<Activity, ActivityDto, String, ActivityDto> {
    @Autowired
    ActivityController(ActivityService service, ActivityMapper mapper) {
        super(service, mapper);
    }

}
