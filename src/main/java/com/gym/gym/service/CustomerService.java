package com.gym.gym.service;

import com.gym.gym.base.repository.FilterableRepository;
import com.gym.gym.base.service.BaseService;
import com.gym.gym.base.service.IBaseService;
import com.gym.gym.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gym.gym.repository.CustomerRepository;


@Service
public class CustomerService extends BaseService<Customer, String> implements IBaseService<Customer, String> {
    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository, FilterableRepository filterableRepository) {
        super(customerRepository, filterableRepository);
        this.customerRepository = customerRepository;
    }
}

