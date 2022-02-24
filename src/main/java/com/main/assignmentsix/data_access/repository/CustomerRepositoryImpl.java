package com.main.assignmentsix.data_access.repository;

import com.main.assignmentsix.data_access.DatabaseConnectionFactory;
import com.main.assignmentsix.model.*;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CustomerRepositoryImpl implements CustomerRepository {
    private final DatabaseConnectionFactory connectionFactory;
    private Connection conn = null;

    public CustomerRepositoryImpl(DatabaseConnectionFactory connectionFactory){
        this.connectionFactory = connectionFactory;}

    @Override
    public Customer getCustomerById(String customerId){
        // Method that fetches the attributes in the SQL query below from the Customer-table by the CustomerId
        Customer customer = null;
        try{
            // Connect to DB
            conn = connectionFactory.getConnection();
            System.out.println("Connection to SQLite has been established.");

            // Make SQL query
            PreparedStatement preparedStatement =
                    conn.prepareStatement("SELECT CustomerId, FirstName, LastName, Country, PostalCode, Phone, Email FROM Customer WHERE CustomerId  = ?");
            preparedStatement.setString(1,customerId);
            // Execute Query
            ResultSet resultSet = preparedStatement.executeQuery();

            // Retrieve the information in the column properties from the database
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
            if(customer == null){
                // If the input of CustomerId doesn't exist in the database, return this string
                System.out.println("CustomerID invalid!");
                return null;
            }
            System.out.println("Select specific customer by ID successful");
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
        // Method that fetches a list of the attributes in the SQL query below from the Customer-table by the CustomerId
        List<Customer> customerList = new ArrayList<>();
        try{
            // Connect to DB
            conn = connectionFactory.getConnection();
            System.out.println("Connection to SQLite has been established.");

            // Make SQL query
            PreparedStatement preparedStatement =
                    conn.prepareStatement("SELECT CustomerId, FirstName, LastName, Country, PostalCode, Phone, Email FROM Customer");
            // Execute Query
            ResultSet resultSet = preparedStatement.executeQuery();

            // Retrieve the information in the column properties from the database
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
        // Method that fetches the attributes in the SQL query below from the Customer-table by typing in a part of a first name
        Customer customer = null;
        try{
            // Connect to DB
            conn = connectionFactory.getConnection();
            System.out.println("Connection to SQLite has been established.");

            // Make SQL query
            PreparedStatement preparedStatement =
                    conn.prepareStatement("SELECT CustomerId, FirstName, LastName, Country, PostalCode, Phone, Email FROM Customer WHERE FirstName LIKE ? ");
            preparedStatement.setString(1,"%"+firstName+"%");
            // Execute Query
            ResultSet resultSet = preparedStatement.executeQuery();

            // Retrieve the information in the column properties from the database
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
            System.out.println("Select specific customer by firstname successful");
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
    public List<Customer> getSpecificAmountOfCustomers(String limit,String offset) {
        // Method that fetches a sequence of attributes in the SQL query below from the Customer-table by limiting into a number of customers starting from a specific CustomerId
        List<Customer> customerList = new ArrayList<>();
        try{
            // Connect to DB
            conn = connectionFactory.getConnection();
            System.out.println("Connection to SQLite has been established.");

            // Make SQL query
            PreparedStatement preparedStatement =
                    conn.prepareStatement("SELECT CustomerId, FirstName, LastName, Country, PostalCode, Phone, Email FROM Customer LIMIT ? OFFSET ?");
            preparedStatement.setString(1,limit);
            preparedStatement.setString(2,offset);

            // Execute Query
            ResultSet resultSet = preparedStatement.executeQuery();

            // Retrieve the information in the column properties from the database
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
            System.out.println("Select customers within chosen limit and offset successful");
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
    public boolean addCustomer(Customer newCustomer) {
        // Method that adds a new customer with the attributes in the SQL query below into the database
        boolean result = false;
        try{
            // Connect to DB
            conn = connectionFactory.getConnection();
            System.out.println("Connection to SQLite has been established.");

            // Make SQL query
            PreparedStatement preparedStatement =
                    conn.prepareStatement("INSERT INTO Customer(FirstName, LastName, Country, PostalCode, Phone, Email) VALUES(?, ?, ?, ?, ?, ?)");
            preparedStatement.setString(1, newCustomer.getFirstName());
            preparedStatement.setString(2, newCustomer.getLastName());
            preparedStatement.setString(3, newCustomer.getCountry());
            preparedStatement.setString(4, newCustomer.getPostalCode());
            preparedStatement.setString(5, newCustomer.getPhone());
            preparedStatement.setString(6, newCustomer.getEmail());

            // Execute Query
            preparedStatement.execute();

            System.out.println("Add new customer successful");
            result = true;
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
        return result;
    }

    @Override
    public boolean updateCustomer(Customer customer){
        // Method that updates the customer's attributes in the SQL query below
        boolean result = false;
        try{
            // Connect to DB
            conn = connectionFactory.getConnection();
            System.out.println("Connection to SQLite has been established.");

            // Make SQL query
            PreparedStatement preparedStatement =
                    conn.prepareStatement("UPDATE Customer SET FirstName = ?, LastName = ?, Country = ?, PostalCode = ?, Phone = ?, Email = ? WHERE CustomerId = ?");
            preparedStatement.setString(1, customer.getFirstName());
            preparedStatement.setString(2, customer.getLastName());
            preparedStatement.setString(3, customer.getCountry());
            preparedStatement.setString(4, customer.getPostalCode());
            preparedStatement.setString(5, customer.getPhone());
            preparedStatement.setString(6, customer.getEmail());
            preparedStatement.setInt(7, customer.getCustomerId());

            // Execute Query
            preparedStatement.execute();

            System.out.println("Update customer successful");
            result = true;
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
        return result;
    }

    @Override
    public List<CustomerCountry> getCustomerCountry() {
        // Method that fetches a list of the attributes in the SQL query below from the Customer-table in a descending order
        List<CustomerCountry> customerCountryList = new ArrayList<>();
        try{
            // Connect to DB
            conn = connectionFactory.getConnection();
            System.out.println("Connection to SQLite has been established.");

            // Make SQL query
            PreparedStatement preparedStatement =
                    conn.prepareStatement("SELECT COUNT(Country), Country FROM Customer GROUP BY Country ORDER BY COUNT(Country) DESC");

            // Execute Query
            ResultSet resultSet = preparedStatement.executeQuery();

            // Retrieve the information in the column properties from the database
            while (resultSet.next()) {
                customerCountryList.add(new CustomerCountry(
                        resultSet.getInt("COUNT(Country)"),
                        resultSet.getString("Country")
                ));
            }
            System.out.println("Select customers within chosen limit and offset successful");
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
        return customerCountryList;
    }

    @Override
    public List<CustomerSpender> getCustomerSpender() {
        // Method that fetches a list of the attributes in the SQL query below from the Customer-table in a descending order
        List<CustomerSpender> customerSpenderList = new ArrayList<>();
        try{
            // Connect to DB
            conn = connectionFactory.getConnection();
            System.out.println("Connection to SQLite has been established.");

            // Make SQL query
            PreparedStatement preparedStatement =
                    conn.prepareStatement("SELECT SUM(Total), CustomerId FROM Invoice GROUP BY CustomerId ORDER BY SUM(Total) DESC");

            // Execute Query
            ResultSet resultSet = preparedStatement.executeQuery();

            // Retrieve the information in the column properties from the database
            while (resultSet.next()) {
                customerSpenderList.add(new CustomerSpender(
                        resultSet.getInt("CustomerId"),
                        resultSet.getDouble("SUM(Total)")
                ));
            }
            System.out.println("Select customer's spending successful");
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
        return customerSpenderList;
    }

    @Override
    public CustomerGenre getCustomerGenre(String customerId) {
        // Method that fetches the attributes in the SQL query below from the Customer-table by the CustomerId
        CustomerGenre customer = null;
        try{
            // Connect to DB
            conn = connectionFactory.getConnection();
            System.out.println("Connection to SQLite has been established.");

            // Make SQL query
            PreparedStatement preparedStatement =
                    conn.prepareStatement("WITH y AS (SELECT COUNT(Track.TrackId) AS GenreCount, Genre.Name AS GenreName\n" +
                            "FROM Track, Genre, Customer, Invoice, InvoiceLine\n" +
                            "WHERE Customer.CustomerId = Invoice.CustomerId\n" +
                            "AND Invoice.InvoiceId = InvoiceLine.InvoiceId\n" +
                            "AND Track.TrackId = InvoiceLine.TrackId\n" +
                            "AND Genre.GenreId = Track.GenreId\n" +
                            "AND Customer.CustomerId = ?\n" +
                            "GROUP BY GenreName\n" +
                            "ORDER BY GenreCount DESC)\n" +
                            "SELECT GenreCount, GenreName\n" +
                            "FROM y\n" +
                            "WHERE (SELECT MAX(GenreCount) from y) = GenreCount");
            preparedStatement.setString(1, customerId);

            // Execute Query
            ResultSet resultSet = preparedStatement.executeQuery();

            // Retrieve the information in the column properties from the database
            ArrayList<String> genres = new ArrayList<>();
            while (resultSet.next()) {
                genres.add(
                        resultSet.getString("GenreName")
                );
            }
            customer = new CustomerGenre(genres);
            System.out.println("Select customer's most popular genres successful");
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
}
