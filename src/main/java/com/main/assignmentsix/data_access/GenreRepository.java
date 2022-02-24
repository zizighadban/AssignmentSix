package com.main.assignmentsix.data_access;

import com.main.assignmentsix.model.Genre;

import java.util.List;

public interface GenreRepository {

    public List<Genre> getFiveRandomGenres();
}
