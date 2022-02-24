package com.main.assignmentsix.controller;

import com.main.assignmentsix.model.Customer;
import com.main.assignmentsix.data_access.repository.CustomerRepositoryImpl;
import com.main.assignmentsix.model.CustomerCountry;
import com.main.assignmentsix.model.CustomerGenre;
import com.main.assignmentsix.model.CustomerSpender;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api")
public class CustomerController {

    private CustomerRepositoryImpl customerRepository;

    public CustomerController(
            CustomerRepositoryImpl customerRepository
    ){
        this.customerRepository = customerRepository;
    }

    //Maps a method to a URL for a GET-request taking in a variable and returning the result. Method returns a customer corresponding the parameter fed in, customer's ID.
    @RequestMapping(value="customer/{id}", method = RequestMethod.GET)
    public Customer getCustomerById(@PathVariable String id){
        return customerRepository.getCustomerById(id);
    }

    //Maps a method for a GET-request that returns a list of all customers as JSON-objects.
    @RequestMapping(value="customer", method = RequestMethod.GET)
    public List<Customer> getAllCustomers(){
        return customerRepository.getAllCustomers();
    }

    //Maps a method to a URL for a GET-request that takes a variable and returns a customer corresponding to the parameter that was fed in, customer's name.
    @RequestMapping(value="customer/firstname/{firstName}", method = RequestMethod.GET)
    public Customer getCustomerByFirstName(@PathVariable String firstName){
        return customerRepository.getCustomerByFirstName(firstName);
    }

    //Maps a method to a URL for a GET-request that takes two parameters that determine how many customers are returned.
    @RequestMapping(value="customer/{limit}/{offset}", method = RequestMethod.GET)
    public List<Customer> getSpecificAmountOfCustomers(@PathVariable String limit, @PathVariable String offset){
        return customerRepository.getSpecificAmountOfCustomers(limit, offset);
    }

    //Maps a method to a URL for a POST-request that inserts a customer into the database.
    @RequestMapping(value="customer", method = RequestMethod.POST)
    public boolean addCustomer(@RequestBody Customer customer){
        return customerRepository.addCustomer(customer);
    }

    //Maps a method to a URL for a PUT-request that updates the values of a customer in the database.
    @RequestMapping(value="customer", method = RequestMethod.PUT)
    public boolean updateCustomer(@RequestBody Customer customer){
        if(getCustomerById(""+customer.getCustomerId()) == null){
            return false;
        }
        return customerRepository.updateCustomer(customer);
    }

    //Maps a method to a URL for a GET-request that shows how many customer's exist in each country represented in the database, ordered from countries with most customers to fewest.
    @RequestMapping(value = "customer/country", method = RequestMethod.GET)
    public List<CustomerCountry> getCustomerCountry(){
        return customerRepository.getCustomerCountry();
    }

    //Maps a method to a URL for a GET-request that shows the summed total invoice value for each customer from highest to lowest.
    @RequestMapping(value = "customer/spender", method = RequestMethod.GET)
    public List<CustomerSpender> getCustomerSpender(){
        return customerRepository.getCustomerSpender();
    }

    //Maps a method to a URL for a GET-request that takes a parameter and returns a customer's favourite genre.
    @RequestMapping(value = "customer/genre/{id}", method = RequestMethod.GET)
    public CustomerGenre getCustomerGenre(@PathVariable String id){
        return customerRepository.getCustomerGenre(id);
    }

}
