package com.main.assignmentsix.controllers;

import com.main.assignmentsix.models.Customer;
import com.main.assignmentsix.data_access.CustomerRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api")
public class CustomerController {

    private CustomerRepository customerRepository;

    public CustomerController(
            CustomerRepository customerRepository
    ){
        this.customerRepository = customerRepository;
    }


    @RequestMapping(value="customer/{id}", method = RequestMethod.GET)
    public Customer getCustomerById(@PathVariable String id){
        return customerRepository.getCustomerById(id);
    }

    @RequestMapping(value="customer", method = RequestMethod.GET)
    public List<Customer> getAllCustomers(){return customerRepository.getAllCustomers();}

    @RequestMapping(value="customer/firstname/{firstName}", method = RequestMethod.GET)
    public Customer getCustomerByFirstName(@PathVariable String firstName){return customerRepository.getCustomerByFirstName(firstName);}

    @RequestMapping(value="customer/{limit}/{offset}", method = RequestMethod.GET)
    public List<Customer> getSpecificAmountOfCustomers(@PathVariable String limit, @PathVariable String offset){
        return customerRepository.getSpecificAmountOfCustomers(limit, offset);}

    @RequestMapping(value="customer", consumes = "application/json", produces = "application/json", method = RequestMethod.POST)
    public boolean addCustomer(@RequestBody Customer customer){return customerRepository.addCustomer(customer);}
}
