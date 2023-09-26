package com.gym.gym.controller.enumControllers;

import com.gym.gym.base.controller.enumControllers.BaseIdCodeDescriptionController;
import com.gym.gym.base.model.DropdownDto;
import com.gym.gym.base.model.restresponse.RestResponse;
import com.gym.gym.enums.Roles;
import com.gym.gym.exception.AppException;
import com.gym.gym.utils.Utils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/roles")
public class RolesController implements BaseIdCodeDescriptionController<Roles> {

    @GetMapping(value = "/dropdown")
    public RestResponse<List<DropdownDto>> getBarcodeEventsDropdown() throws AppException {
        return new RestResponse<>(Utils.rolesList());
    }
}
