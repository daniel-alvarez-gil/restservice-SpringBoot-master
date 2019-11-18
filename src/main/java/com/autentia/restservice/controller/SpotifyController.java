/*
package com.autentia.restservice.controller;

import com.autentia.restservice.model.Spotify;
import com.autentia.restservice.service.SpotifyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/spotify")
@Api(value = "Song Resource", description = "This API has a Resttemplate for Spotify")
public class SpotifyController {

    @Autowired
    SpotifyService spotifyService;

    @GetMapping("/{spotifyArtistId}")
    @ApiOperation(value = "Find a Spotify Artist", notes = "Return a Spotify Artist",response = Spotify.class)
    private Spotify getArtist(@PathVariable("spotifyArtistId") String spotifyArtistId) {
        return spotifyService.getArtist(spotifyArtistId);
    }

}*/
