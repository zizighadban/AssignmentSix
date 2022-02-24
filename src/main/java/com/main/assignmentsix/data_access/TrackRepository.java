package com.main.assignmentsix.data_access;

import com.main.assignmentsix.model.Song;
import com.main.assignmentsix.model.Track;

import java.util.List;

public interface TrackRepository {

    public List<Song> getFiveRandomSongs();

    public List<Track> getTrackByName(Song song);
}
