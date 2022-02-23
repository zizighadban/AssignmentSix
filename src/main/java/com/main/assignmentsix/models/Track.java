package com.main.assignmentsix.models;

public class Track {
    private Artist artist;
    private String album;
    private Song song;
    private Genre genre;

    public Track(Artist artist, String album, Song song, Genre genre) {
        this.artist = artist;
        this.album = album;
        this.song = song;
        this.genre = genre;
    }

    public Artist getArtist() {
        return artist;
    }

    public String getAlbum() {
        return album;
    }

    public Song getSong() {
        return song;
    }

    public Genre getGenre() {
        return genre;
    }
}
