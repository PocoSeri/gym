package com.gym.gym.controller;

import com.gym.gym.base.controller.BaseController;
import com.gym.gym.base.utils.ControllerMethod;
import com.gym.gym.base.utils.annotation.ControllerAllowedMethods;
import com.gym.gym.entity.Customer;
import com.gym.gym.mapping.CustomerMapper;
import com.gym.gym.model.CustomerDto;
import com.gym.gym.repository.service.CustomerService;
import com.gym.gym.swagger.OpenApiConfig;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ControllerAllowedMethods(httpMethod = {ControllerMethod.GET_ONE, ControllerMethod.GET_LIST, ControllerMethod.UPDATE, ControllerMethod.INSERT, ControllerMethod.DELETE})
@RequestMapping("/api/customers")
@SecurityRequirement(name = OpenApiConfig.SECURITY_SCHEMA_NAME)
@Tag(name = "Customer")
public class CustomerController extends BaseController<Customer, CustomerDto, String, CustomerDto> {
    @Autowired
    CustomerController(CustomerService customerService, CustomerMapper customerMapper) {
        super(customerService, customerMapper);
    }

}
