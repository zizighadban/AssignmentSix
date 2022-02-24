package com.main.assignmentsix.data_access.service;

import com.main.assignmentsix.data_access.repository.TrackRepositoryImpl;
import com.main.assignmentsix.model.Song;
import com.main.assignmentsix.model.Track;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrackService {
    private final TrackRepositoryImpl trackRepository;

    public TrackService(TrackRepositoryImpl trackRepository){this.trackRepository = trackRepository;}

    public List<Song> getFiveRandomSongs(){
        return trackRepository.getFiveRandomSongs();
    }
    public List<Track> getTrackByName(Song song){
        return trackRepository.getTrackByName(song);
    }
}
