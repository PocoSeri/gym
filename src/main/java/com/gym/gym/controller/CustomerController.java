package com.gym.gym.controller;

import com.gym.gym.base.controller.BaseController;
import com.gym.gym.entity.Customer;
import com.gym.gym.mapping.CustomerMapper;
import com.gym.gym.utils.ControllerMethod;
import com.gym.gym.utils.annotation.ControllerAllowedMethods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.gym.gym.service.CustomerService;

@RestController
@ControllerAllowedMethods(httpMethod = {ControllerMethod.GET_ONE, ControllerMethod.GET_LIST, ControllerMethod.UPDATE, ControllerMethod.INSERT, ControllerMethod.DELETE})
@RequestMapping("/api/customers")
public class CustomerController extends BaseController<Customer, String, Customer, Customer> {
    @Autowired
    CustomerController(CustomerService customerService, CustomerMapper customerMapper) {
        super(customerService, customerMapper);
    }

//    @PostMapping
//    public void save(@RequestBody Customer customer) {
//        customerService.insert(customer);
//    }
//
//    @GetMapping("")
//    public ResponseEntity<List<Customer>> getAll() throws AppException {
//        return ResponseEntity.ok().body(customerService.getAll());
//    }
//
//    @PutMapping("")
//    public ResponseEntity<?> update(@RequestBody Customer customer) throws AppException {
//        try {
//            customerService.update(customer);
//        } catch(Exception e) {
//            return ResponseEntity.notFound().build();
//        }
//        return ResponseEntity.ok().body(customer);
//    }
}
