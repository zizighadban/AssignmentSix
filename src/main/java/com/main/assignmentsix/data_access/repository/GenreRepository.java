package com.main.assignmentsix.data_access.repository;

import com.main.assignmentsix.model.Genre;

import java.util.List;

public interface GenreRepository {

    public List<Genre> getFiveRandomGenres();
}
