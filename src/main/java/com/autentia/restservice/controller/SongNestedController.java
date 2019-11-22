package com.autentia.restservice.controller;

import com.autentia.restservice.dto.SongPrinceOnly;
import com.autentia.restservice.model.Album;
import com.autentia.restservice.model.Artist;
import com.autentia.restservice.model.Song;
import com.autentia.restservice.service.ArtistService;
import com.autentia.restservice.service.SongService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/artists/{artistId}/albums/{albumId}/songs")
@Api(value = "Song Resource", description = "This API has a CRUD for Song")
public class SongNestedController {

    @Autowired
    ArtistService artistService;

    @Autowired
    SongService songService;

    @GetMapping()
    @ApiOperation(value = "Find all songs from artist's album", notes = "Return all songs" )
    private ResponseEntity getAllSongs(@PathVariable("artistId") Long artistId, @PathVariable("albumId") Long albumId) {

        return new ResponseEntity<>(artistService.getSongsFromArtistAlbum(artistId, albumId), HttpStatus.OK);
    }

    @PostMapping()
    @ApiOperation(value = "Save a song from artist's album", notes = "Return the song" )
    public ResponseEntity saveSong(@PathVariable(value = "artistId") Long artistId, @PathVariable("albumId") Long albumId,
                         @RequestBody Song song) {
        return new ResponseEntity<>(artistService.saveSongsFromArtistAlbum(artistId, albumId, song), HttpStatus.OK);
    }


}
