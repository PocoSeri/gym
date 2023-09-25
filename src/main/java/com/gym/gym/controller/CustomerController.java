package com.gym.gym.controller;

import com.gym.gym.base.controller.BaseController;
import com.gym.gym.base.model.BaseModel;
import com.gym.gym.entity.Customer;
import com.gym.gym.mapping.CustomerMapper;
import com.gym.gym.model.CustomerDto;
import com.gym.gym.utils.ControllerMethod;
import com.gym.gym.utils.annotation.ControllerAllowedMethods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.gym.gym.service.CustomerService;

@RestController
@ControllerAllowedMethods(httpMethod = {ControllerMethod.GET_ONE, ControllerMethod.GET_LIST, ControllerMethod.UPDATE, ControllerMethod.INSERT, ControllerMethod.DELETE})
@RequestMapping("/api/customers")
public class CustomerController extends BaseController<Customer, CustomerDto, String, CustomerDto> {
    @Autowired
    CustomerController(CustomerService customerService, CustomerMapper customerMapper) {
        super(customerService, customerMapper);
    }

}
