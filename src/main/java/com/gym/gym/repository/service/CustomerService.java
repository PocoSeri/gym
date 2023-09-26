package com.gym.gym.repository.service;

import com.gym.gym.base.repository.FilterableRepository;
import com.gym.gym.base.service.BaseService;
import com.gym.gym.base.service.IBaseService;
import com.gym.gym.entity.Customer;
import com.gym.gym.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CustomerService extends BaseService<Customer, String> implements IBaseService<Customer, String> {
    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository, FilterableRepository filterableRepository) {
        super(customerRepository, filterableRepository);
        this.customerRepository = customerRepository;
    }
}

