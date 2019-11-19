package com.autentia.restservice.service;

import com.autentia.restservice.model.Album;
import com.autentia.restservice.model.Artist;
import com.autentia.restservice.repository.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArtistService {

    @Autowired
    ArtistRepository artistRepository;

    public List<Artist> getAllArtists() {
        List<Artist> artists = new ArrayList<>();
        artistRepository.findAll().forEach(artist -> artists.add(artist));
        return artists;
    }

    public Artist getArtistById(long id) {
        return artistRepository.findById(id).get();
    }

    public void saveOrUpdate(Artist artist) {
        artistRepository.save(artist);
    }

    public void delete(long id) {
        artistRepository.deleteById(id);
    }
}