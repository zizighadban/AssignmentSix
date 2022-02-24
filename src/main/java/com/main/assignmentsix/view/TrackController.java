package com.main.assignmentsix.view;

import com.main.assignmentsix.model.Song;
import com.main.assignmentsix.service.ArtistService;
import com.main.assignmentsix.service.GenreService;
import com.main.assignmentsix.service.TrackService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.websocket.server.PathParam;

@Controller
public class TrackController {
    private final ArtistService artistService;
    private final TrackService trackService;
    private final GenreService genreService;

    public TrackController(ArtistService artistService, TrackService trackService, GenreService genreService) {
        this.artistService = artistService;
        this.trackService = trackService;
        this.genreService = genreService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String view(
            Model model
    ){
        model.addAttribute("artists", artistService.getFiveRandomArtists());
        model.addAttribute("songs", trackService.getFiveRandomSongs());
        model.addAttribute("genres", genreService.getFiveRandomGenres());
        return "songList";
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String searchView(
            Model model,
            @PathParam("param") String param
    ){
        Song song = new Song(param);
        model.addAttribute("searchResult", trackService.getTrackByName(song));
        return "songList";
    }
}
