package com.main.assignmentsix.data_access;

import com.main.assignmentsix.model.Artist;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ArtistRepositoryImpl implements ArtistRepository{
    Connection conn = null;

    @Override
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
}
