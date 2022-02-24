package com.main.assignmentsix.view;

import com.main.assignmentsix.model.Song;
import com.main.assignmentsix.data_access.service.ArtistService;
import com.main.assignmentsix.data_access.service.GenreService;
import com.main.assignmentsix.data_access.service.TrackService;
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
        //When start page is loaded a model is sent to thymeleaf-html along with 5 random artists, songs and genres.
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
        //When a search is executed on start page the start page is reloaded but without the random artists, songs and genres and instead takes a list of songs from the search criteria.
        //If the search executed had nothing written in the search field the default start page is loaded instead.
        if(param.length() == 0){
            return(view(model));
        }
        Song song = new Song(param);
        model.addAttribute("searchResult", trackService.getTrackByName(song));
        return "songList";
    }
}
