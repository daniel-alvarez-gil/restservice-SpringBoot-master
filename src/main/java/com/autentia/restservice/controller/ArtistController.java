package com.autentia.restservice.controller;

import com.autentia.restservice.model.Artist;
import com.autentia.restservice.service.ArtistService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/artists")
@Api(value = "Artist Resource", description = "This API has a CRUD for Artists")
public class ArtistController {

    @Autowired
    ArtistService artistService;

    @GetMapping()
    @ApiOperation(value = "Find all artists", notes = "Return all artists" )
    private List<Artist> getAllArtists() {
        return artistService.getAllArtists();
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Find an artist", notes = "Return an artist by Id",response = Artist.class)
    private Artist getArtist(@PathVariable("id") long id) {
        return artistService.getArtistById(id);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete an artist")
    private void deleteArtist(@PathVariable("id") long id) {
        artistService.delete(id);
    }

    @PostMapping()
    @ApiOperation(value = "Save an artist", notes = "Return an artist Id" )
    private long saveArtist(@RequestBody Artist artist) {
        artistService.saveOrUpdate(artist);
        return artist.getId();
    }

    @PutMapping()
    @ApiOperation(value = "Save or Update an artist", notes = "Return an artist Id" )
    private long updateArtist(@RequestBody Artist artist) {
        artistService.saveOrUpdate(artist);
        return artist.getId();
    }
}
