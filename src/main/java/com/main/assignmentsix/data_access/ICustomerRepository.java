package com.main.assignmentsix.data_access;

import com.main.assignmentsix.models.Customer;
import com.main.assignmentsix.models.CustomerCountry;
import com.main.assignmentsix.models.CustomerGenre;
import com.main.assignmentsix.models.CustomerSpender;

import java.util.List;

public interface ICustomerRepository {

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
