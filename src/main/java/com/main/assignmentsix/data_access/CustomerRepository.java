package com.main.assignmentsix.data_access;

import com.main.assignmentsix.ConnectionHelper;
import com.main.assignmentsix.models.Customer;
import com.main.assignmentsix.models.CustomerCountry;
import com.main.assignmentsix.models.CustomerGenre;
import com.main.assignmentsix.models.CustomerSpender;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CustomerRepository implements ICustomerRepository{
    private String URL = ConnectionHelper.CONNECTION_URL;
    private Connection conn = null;

    @Override
    public Customer getCustomerById(String customerId){
        Customer customer = null;
        try{
            // Connect to DB
            conn = ConnectionFactory.getConnection();
            System.out.println("Connection to SQLite has been established.");

            // Make SQL query
            PreparedStatement preparedStatement =
                    conn.prepareStatement("SELECT CustomerId, FirstName, LastName, Country, PostalCode, Phone, Email FROM Customer WHERE CustomerId  = ?");
            preparedStatement.setString(1,customerId);
            // Execute Query
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                customer = new Customer(
                        resultSet.getInt("CustomerId"),
                        resultSet.getString("FirstName"),
                        resultSet.getString("LastName"),
                        resultSet.getString("Country"),
                        resultSet.getString("PostalCode"),
                        resultSet.getString("Phone"),
                        resultSet.getString("Email")
                );
            }
            System.out.println("Select specific customer successful");
        }
        catch (Exception exception){
            System.out.println(exception.getMessage());
        }
        finally {
            try {
                conn.close();
            }
            catch (Exception exception){
                System.out.println(exception.getMessage());
            }
        }
        return customer;
    }

    @Override
    public List<Customer> getAllCustomers() {
        List<Customer> customerList = new ArrayList<>();
        try{
            // Connect to DB
            conn = ConnectionFactory.getConnection();
            System.out.println("Connection to SQLite has been established.");

            // Make SQL query
            PreparedStatement preparedStatement =
                    conn.prepareStatement("SELECT CustomerId, FirstName, LastName, Country, PostalCode, Phone, Email FROM Customer");
            // Execute Query
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                customerList.add(new Customer(
                        resultSet.getInt("CustomerId"),
                        resultSet.getString("FirstName"),
                        resultSet.getString("LastName"),
                        resultSet.getString("Country"),
                        resultSet.getString("PostalCode"),
                        resultSet.getString("Phone"),
                        resultSet.getString("Email")
                ));
            }
            System.out.println("Select all customers successful");
        }
        catch (Exception exception){
            System.out.println(exception.getMessage());
        }
        finally {
            try {
                conn.close();
            }
            catch (Exception exception){
                System.out.println(exception.getMessage());
            }
        }
        return customerList;
    }

    @Override
    public Customer getCustomerByFirstName(String firstName) {
        Customer customer = null;
        try{
            // Connect to DB
            conn = ConnectionFactory.getConnection();
            System.out.println("Connection to SQLite has been established.");

            // Make SQL query
            PreparedStatement preparedStatement =
                    conn.prepareStatement("SELECT CustomerId, FirstName, LastName, Country, PostalCode, Phone, Email FROM Customer WHERE FirstName LIKE ? ");
            preparedStatement.setString(1,"%"+firstName+"%");
            // Execute Query
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                customer = new Customer(
                        resultSet.getInt("CustomerId"),
                        resultSet.getString("FirstName"),
                        resultSet.getString("LastName"),
                        resultSet.getString("Country"),
                        resultSet.getString("PostalCode"),
                        resultSet.getString("Phone"),
                        resultSet.getString("Email")
                );
            }
            System.out.println("Select specific customer successful");
        }
        catch (Exception exception){
            System.out.println(exception.getMessage());
        }
        finally {
            try {
                conn.close();
            }
            catch (Exception exception){
                System.out.println(exception.getMessage());
            }
        }
        return customer;
    }

    @Override
    public List<Customer> getSpecificAmountOfCustomer(int limit, int offset) {
        return null;
    }

    @Override
    public boolean addCustomer(Customer newCustomer) {
        return false;
    }

    @Override
    public List<CustomerCountry> getCustomerCountry() {
        return null;
    }

    @Override
    public List<CustomerSpender> getCustomerSpender() {
        return null;
    }

    @Override
    public CustomerGenre getCustomerGenre(int customerId) {
        return null;
    }
}
