package com.main.assignmentsix.data_access.repository;

import com.main.assignmentsix.model.Customer;
import com.main.assignmentsix.model.CustomerCountry;
import com.main.assignmentsix.model.CustomerGenre;
import com.main.assignmentsix.model.CustomerSpender;

import java.util.List;

public interface CustomerRepository {

    public Customer getCustomerById(String customerId);
    public List<Customer> getAllCustomers();
    public Customer getCustomerByFirstName(String firstName);
    public List<Customer> getSpecificAmountOfCustomers(String limit, String offset);
    public boolean addCustomer(Customer newCustomer);
    public boolean updateCustomer(Customer customer);
    public List<CustomerCountry> getCustomerCountry();
    public List<CustomerSpender> getCustomerSpender();
    public CustomerGenre getCustomerGenre(String customerId);
}
