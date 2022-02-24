package com.main.assignmentsix.service;

import com.main.assignmentsix.data_access.GenreRepositoryImpl;
import com.main.assignmentsix.model.Genre;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreService {
    private final GenreRepositoryImpl genreRepository;

    public GenreService(GenreRepositoryImpl genreRepository) {
        this.genreRepository = genreRepository;
    }

    public List<Genre> getFiveRandomGenres(){
        return genreRepository.getFiveRandomGenres();
    }
}
