package com.main.assignmentsix.data_access;

import com.main.assignmentsix.ConnectionHelper;
import com.main.assignmentsix.models.*;
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
            if(customer == null){
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
        List<Customer> customerList = new ArrayList<>();
        try{
            // Connect to DB
            conn = ConnectionFactory.getConnection();
            System.out.println("Connection to SQLite has been established.");

            // Make SQL query
            PreparedStatement preparedStatement =
                    conn.prepareStatement("SELECT CustomerId, FirstName, LastName, Country, PostalCode, Phone, Email FROM Customer LIMIT ? OFFSET ?");
            preparedStatement.setString(1,limit);
            preparedStatement.setString(2,offset);

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
        boolean result = false;
        try{
            // Connect to DB
            conn = ConnectionFactory.getConnection();
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
        boolean result = false;
        try{
            // Connect to DB
            conn = ConnectionFactory.getConnection();
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
        List<CustomerCountry> customerCountryList = new ArrayList<>();
        try{
            // Connect to DB
            conn = ConnectionFactory.getConnection();
            System.out.println("Connection to SQLite has been established.");

            // Make SQL query
            PreparedStatement preparedStatement =
                    conn.prepareStatement("SELECT COUNT(Country), Country FROM Customer GROUP BY Country ORDER BY COUNT(Country) DESC");

            // Execute Query
            ResultSet resultSet = preparedStatement.executeQuery();

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
        List<CustomerSpender> customerSpenderList = new ArrayList<>();
        try{
            // Connect to DB
            conn = ConnectionFactory.getConnection();
            System.out.println("Connection to SQLite has been established.");

            // Make SQL query
            PreparedStatement preparedStatement =
                    conn.prepareStatement("SELECT SUM(Total), CustomerId FROM Invoice GROUP BY CustomerId ORDER BY SUM(Total) DESC");

            // Execute Query
            ResultSet resultSet = preparedStatement.executeQuery();

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
        CustomerGenre customer = null;
        try{
            // Connect to DB
            conn = ConnectionFactory.getConnection();
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

    public List<Artist> getFiveRandomArtists(){
        List<Artist> artistList = new ArrayList<>();
        try{
            // Connect to DB
            conn = ConnectionFactory.getConnection();
            System.out.println("Connection to SQLite has been established.");

            // Make SQL query
            PreparedStatement preparedStatement =
                    conn.prepareStatement("SELECT Name FROM Artist ORDER BY RANDOM() LIMIT 5");

            // Execute Query
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                artistList.add(new Artist(
                        resultSet.getString("Name")
                ));
            }
            System.out.println("Select five random artists successful");
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
        return artistList;
    }

    public List<Genre> getFiveRandomGenres(){
        List<Genre> genreList = new ArrayList<>();
        try{
            // Connect to DB
            conn = ConnectionFactory.getConnection();
            System.out.println("Connection to SQLite has been established.");

            // Make SQL query
            PreparedStatement preparedStatement =
                    conn.prepareStatement("SELECT Name FROM Genre ORDER BY RANDOM() LIMIT 5");

            // Execute Query
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                genreList.add(new Genre(
                        resultSet.getString("Name")
                ));
            }
            System.out.println("Select five random genres successful");
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
        return genreList;
    }

    public List<Song> getFiveRandomSongs(){
        List<Song> songList = new ArrayList<>();
        try{
            // Connect to DB
            conn = ConnectionFactory.getConnection();
            System.out.println("Connection to SQLite has been established.");

            // Make SQL query
            PreparedStatement preparedStatement =
                    conn.prepareStatement("SELECT Name FROM Track ORDER BY RANDOM() LIMIT 5");

            // Execute Query
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                songList.add(new Song(
                        resultSet.getString("Name")
                ));
            }
            System.out.println("Select five random songs successful");
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
        return songList;
    }

    public List<Track> getTrackByName(Song song){
        List<Track> trackList = new ArrayList<>();
        try{
            // Connect to DB
            conn = ConnectionFactory.getConnection();
            System.out.println("Connection to SQLite has been established.");

            // Make SQL query
            PreparedStatement preparedStatement =
                    conn.prepareStatement("SELECT Genre.Name AS GenreName, Track.Name AS TrackName, Album.Title AS AlbumName, Artist.Name AS ArtistName FROM Genre, Track, Album, Artist WHERE TrackName LIKE ?");
            preparedStatement.setString(1,"%"+song.getSongName()+"%");

            // Execute Query
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
               trackList.add(new Track(
                       new Artist(resultSet.getString("ArtistName")),
                       resultSet.getString("AlbumName"),
                       new Song(resultSet.getString("TrackName")),
                       new Genre(resultSet.getString("GenreName"))
               ));
            }
            System.out.println("Select track successful");
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
        return trackList;
    }
}
