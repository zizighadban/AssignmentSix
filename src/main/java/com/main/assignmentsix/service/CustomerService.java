package com.main.assignmentsix.service;

import com.main.assignmentsix.data_access.CustomerRepository;
import com.main.assignmentsix.models.Artist;
import com.main.assignmentsix.models.Genre;
import com.main.assignmentsix.models.Song;
import com.main.assignmentsix.models.Track;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Artist> getFiveRandomArtists(){
        return customerRepository.getFiveRandomArtists();
    }

    public List<Song> getFiveRandomSongs(){
        return customerRepository.getFiveRandomSongs();
    }
    public List<Genre> getFiveRandomGenres(){
        return customerRepository.getFiveRandomGenres();
    }

    public List<Track> getTrackByName(Song song){
        return customerRepository.getTrackByName(song);
    }
}
