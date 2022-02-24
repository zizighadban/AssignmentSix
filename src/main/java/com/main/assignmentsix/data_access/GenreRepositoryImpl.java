package com.main.assignmentsix.data_access;

import com.main.assignmentsix.model.Genre;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Repository
public class GenreRepositoryImpl implements GenreRepository{
    Connection conn = null;

    @Override
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
}
