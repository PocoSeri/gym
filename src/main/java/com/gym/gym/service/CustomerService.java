package com.gym.gym.service;

import com.gym.gym.base.service.BaseService;
import com.gym.gym.entity.Customer;
import com.gym.gym.exception.AppException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import com.gym.gym.repository.CustomerRepository;

import java.util.Optional;

@Service
public class CustomerService extends BaseService<Customer, String> {
    private final CustomerRepository customerRepository;
    private final MongoTemplate mongoTemplate;

    @Autowired
    public CustomerService(CustomerRepository customerRepository, MongoTemplate mongoTemplate) {
        super(customerRepository);
        this.customerRepository = customerRepository;
        this.mongoTemplate = mongoTemplate;
    }

    public void createNewDocument() {
        Customer customer = new Customer();


        customerRepository.save(customer);
    }

    @Override
    public Customer patch(Customer patchEntity) throws AppException {
        return null;
    }

    @Override
    public void delete(String id) throws AppException {

    }
}

