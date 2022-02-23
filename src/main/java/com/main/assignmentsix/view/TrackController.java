package com.main.assignmentsix.view;

import com.main.assignmentsix.models.Song;
import com.main.assignmentsix.service.CustomerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.websocket.server.PathParam;

@Controller
public class TrackController {
    private final CustomerService customerService;

    public TrackController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String view(
            Model model
    ){
        model.addAttribute("artists", customerService.getFiveRandomArtists());
        model.addAttribute("songs", customerService.getFiveRandomSongs());
        model.addAttribute("genres", customerService.getFiveRandomGenres());
        return "songList";
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String searchView(
            Model model,
            @PathParam("param") String param
    ){
        Song song = new Song(param);
        model.addAttribute("searchResult", customerService.getTrackByName(song));
        return "songList";
    }
}
