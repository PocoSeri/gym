package com.gym.gym.controller.enumControllers;

import com.gym.gym.base.controller.enumControllers.BaseIdCodeDescriptionController;
import com.gym.gym.base.model.DropdownDto;
import com.gym.gym.base.model.restresponse.RestResponse;
import com.gym.gym.enums.Roles;
import com.gym.gym.exception.AppException;
import com.gym.gym.swagger.OpenApiConfig;
import com.gym.gym.utils.Utils;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/roles")
@SecurityRequirement(name = OpenApiConfig.SECURITY_SCHEMA_NAME)
@Tag(name = "Roles")
public class RolesController implements BaseIdCodeDescriptionController<Roles> {

    @GetMapping(value = "/dropdown")

    public RestResponse<List<DropdownDto>> getBarcodeEventsDropdown() throws AppException {
        return new RestResponse<>(Utils.rolesList());
    }
}
