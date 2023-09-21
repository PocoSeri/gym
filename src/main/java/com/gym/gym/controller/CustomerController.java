package com.gym.gym.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.gym.gym.service.CustomerService;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    private final CustomerService customerService;
    CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public void save() {
        customerService.createNewDocument();
    }

    @GetMapping("")
    public void getAll() {
        customerService.createNewDocument();
    }
}
