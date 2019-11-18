package com.autentia.restservice.controller;

import com.autentia.restservice.model.Album;
import com.autentia.restservice.service.AlbumService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/{id}")
    @ApiOperation(value = "Find an album", notes = "Return an album by Id",response = Album.class)
    private Album getAlbum(@PathVariable("id") int id) {
        return albumService.getAlbumById(id);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete an album")
    private void deleteAlbum(@PathVariable("id") int id) {
        albumService.delete(id);
    }

    @PostMapping()
    @ApiOperation(value = "Save an album", notes = "Return an album Id" )
    private long saveAlbum(@RequestBody Album album) {
        albumService.saveOrUpdate(album);
        return album.getId();
    }

    @PutMapping()
    @ApiOperation(value = "Save or Update an album", notes = "Return an album Id" )
    private long updateAlbum(@RequestBody Album album) {
        albumService.saveOrUpdate(album);
        return album.getId();
    }
}
