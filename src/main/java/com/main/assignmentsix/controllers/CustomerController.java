package com.main.assignmentsix.controllers;

import com.main.assignmentsix.models.Customer;
import com.main.assignmentsix.repositories.CustomerRepository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;


@RestController
public class CustomerController {

    private CustomerRepository customerRepository = new CustomerRepository();


    @RequestMapping(value="/api/customers/{id}", method = RequestMethod.GET)
    public Customer getCustomerById(@PathVariable String id){
        return customerRepository.getCustomerById(id);
    }

}
