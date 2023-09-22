package com.gym.gym.controller;

import com.gym.gym.base.model.restresponse.RestResponse;
import com.gym.gym.entity.Customer;
import com.gym.gym.exception.AppException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.gym.gym.service.CustomerService;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    private final CustomerService customerService;
    CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public void save(@RequestBody Customer customer) {
        customerService.insert(customer);
    }

    @GetMapping("")
    public ResponseEntity<List<Customer>> getAll() throws AppException {
        return ResponseEntity.ok().body(customerService.getAll());
    }

    @PutMapping("")
    public ResponseEntity<?> update(@RequestBody Customer customer) throws AppException {
        try {
            customerService.update(customer);
        } catch(Exception e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(customer);
    }
}
