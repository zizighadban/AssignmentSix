package com.main.assignmentsix.model;

import java.util.ArrayList;

public class CustomerGenre {
    private ArrayList<String> genre;
    public CustomerGenre(ArrayList<String> genre) {
        this.genre = genre;
    }

    public ArrayList<String> getGenre() {
        return genre;
    }
}
