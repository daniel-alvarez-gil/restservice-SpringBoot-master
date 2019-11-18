package com.autentia.restservice.service;

import com.autentia.restservice.model.Album;
import com.autentia.restservice.repository.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AlbumService {

    @Autowired
    AlbumRepository albumRepository;

    public List<Album> getAllAlbums() {
        List<Album> albums = new ArrayList<>();
        albumRepository.findAll().forEach(album -> albums.add(album));
        return albums;
    }

    public Album getAlbumById(int id) {
        return albumRepository.findById(id).get();
    }

    public void saveOrUpdate(Album album) {
        albumRepository.save(album);
    }

    public void delete(int id) {
        albumRepository.deleteById(id);
    }

}
