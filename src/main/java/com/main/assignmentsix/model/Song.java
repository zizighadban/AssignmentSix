package com.main.assignmentsix.model;

public class Song {
    private String songName;

    public Song(){

    }

    public Song(String songName) {
        this.songName = songName;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }
}
