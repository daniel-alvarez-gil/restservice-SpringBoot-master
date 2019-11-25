package com.autentia.restservice.controller;

import com.autentia.restservice.model.Album;
import com.autentia.restservice.model.Song;
import com.autentia.restservice.service.AlbumService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resources;
import org.springframework.web.bind.annotation.*;


import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/v1/albums")
@Api(value = "Album Resource", description = "This API has a CRUD for Album")
public class AlbumController {

    @Autowired
    AlbumService albumService;

    @GetMapping()
    @ApiOperation(value = "Find all albums", notes = "Return all albums" )
    private List<Album> getAllAlbums() {
        return albumService.getAllAlbums();
    }

    @GetMapping("/hateoas")
    @ApiOperation(value = "Find all albums", notes = "Return all albums" )
    public Resources<Album> getAllAlbumsByHateoas() {
        List<Album> allAlbums = albumService.getAllAlbums();

        for (Album album : allAlbums) {
            long albumId = album.getAlbumId();
            Link selfLink = linkTo(AlbumController.class).slash(albumId).withSelfRel();
            album.add(selfLink);

            List<Song> songs = albumService.getAllSongsForAlbum(albumId);
            if (songs.size() > 0) {
                Link albumLink =
                        linkTo(methodOn(AlbumController.class)
                        .getAllSongByAlbum(albumId)).withRel("allSongs");
                album.add(albumLink);

                for (final Song song : songs) {
                    Link songLink =
                            linkTo(methodOn(SongController.class)
                            .getSong(song.getSongId())).withSelfRel();
                    song.add(songLink);
                }
            }
        }

        Link link = linkTo(AlbumController.class).withSelfRel();
        return new Resources<>(allAlbums, link);
    }

    @GetMapping("/{albumId}/songs")
    @ApiOperation(value = "Find all songs by album", notes = "Return all albums" )
    public List<Song> getAllSongByAlbum(@PathVariable("albumId") long albumId) {
        return albumService.getAllSongsForAlbum(albumId);
    }

    @GetMapping("/{albumId}")
    @ApiOperation(value = "Find an album", notes = "Return an album by Id",response = Album.class)
    private Album getAlbum(@PathVariable("albumId") long albumId) {
        return albumService.getAlbumById(albumId);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete an album")
    private void deleteAlbum(@PathVariable("albumId") int albumId) {
        albumService.delete(albumId);
    }

    @PostMapping()
    @ApiOperation(value = "Save an album", notes = "Return an album Id" )
    private long saveAlbum(@RequestBody Album album) {
        albumService.saveOrUpdate(album);
        return album.getAlbumId();
    }

    @PutMapping()
    @ApiOperation(value = "Save or Update an album", notes = "Return an album Id" )
    private long updateAlbum(@RequestBody Album album) {
        albumService.saveOrUpdate(album);
        return album.getAlbumId();
    }
}
