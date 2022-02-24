package com.main.assignmentsix.data_access;

import com.main.assignmentsix.model.Artist;

import java.util.List;

public interface ArtistRepository {

    public List<Artist> getFiveRandomArtists();
}
