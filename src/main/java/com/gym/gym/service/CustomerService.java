package com.gym.gym.service;

import com.gym.gym.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import com.gym.gym.repository.CustomerRepository;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final MongoTemplate mongoTemplate;

    @Autowired
    public CustomerService(CustomerRepository customerRepository, MongoTemplate mongoTemplate) {
        this.customerRepository = customerRepository;
        this.mongoTemplate = mongoTemplate;
    }

    public void createNewDocument() {
        Customer customer = new Customer();
        customer.setId("1");

        customerRepository.save(customer);
    }
}

