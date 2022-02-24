package com.main.assignmentsix.service;

import com.main.assignmentsix.data_access.ArtistRepositoryImpl;
import com.main.assignmentsix.model.Artist;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArtistService {
    private final ArtistRepositoryImpl artistRepository;

    public ArtistService(ArtistRepositoryImpl artistRepository) {
        this.artistRepository = artistRepository;
    }

    public List<Artist> getFiveRandomArtists(){
        return artistRepository.getFiveRandomArtists();
    }

}
