package com.main.assignmentsix.data_access.repository;

import com.main.assignmentsix.data_access.DatabaseConnectionFactory;
import com.main.assignmentsix.data_access.DevConnectionFactory;
import com.main.assignmentsix.model.Artist;
import com.main.assignmentsix.model.Genre;
import com.main.assignmentsix.model.Song;
import com.main.assignmentsix.model.Track;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TrackRepositoryImpl implements TrackRepository{
    DatabaseConnectionFactory connectionFactory;
    Connection conn = null;

    public TrackRepositoryImpl(DatabaseConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    @Override
    public List<Song> getFiveRandomSongs(){
        // Method that fetches a list of five random songs from the Track-table and limiting it to 5
        List<Song> songList = new ArrayList<>();
        try{
            // Connect to DB
            conn = connectionFactory.getConnection();
            System.out.println("Connection to SQLite has been established.");

            // Make SQL query
            PreparedStatement preparedStatement =
                    conn.prepareStatement("SELECT Name FROM Track ORDER BY RANDOM() LIMIT 5");

            // Execute Query
            ResultSet resultSet = preparedStatement.executeQuery();

            // Retrieve the information in the column property from the database
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

    @Override
    public List<Track> getTrackByName(Song song){
        List<Track> trackList = new ArrayList<>();
        try{
            // Connect to DB
            conn = connectionFactory.getConnection();
            System.out.println("Connection to SQLite has been established.");

            // Make SQL query
            PreparedStatement preparedStatement =
                    conn.prepareStatement("SELECT Genre.Name AS GenreName, Track.Name AS TrackName, Album.Title AS AlbumName, Artist.Name AS ArtistName FROM Genre, Track, Album, Artist WHERE Genre.GenreId = Track.GenreId AND Album.AlbumId = Track.AlbumId AND Artist.ArtistId = Album.ArtistId AND TrackName LIKE ?");
            preparedStatement.setString(1,"%"+song.getSongName()+"%");
            // Execute Query
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                if(!resultSet.getString("TrackName").contains(song.getSongName())){
                    continue;
                }
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
