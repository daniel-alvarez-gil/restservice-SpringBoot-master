package com.autentia.restservice.service;

import com.autentia.restservice.dto.SongPrinceOnly;
import com.autentia.restservice.model.Album;
import com.autentia.restservice.model.Artist;
import com.autentia.restservice.model.Song;
import com.autentia.restservice.repository.AlbumRepository;
import com.autentia.restservice.repository.ArtistRepository;
import com.autentia.restservice.repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArtistService {

    @Autowired
    ArtistRepository artistRepository;

    @Autowired
    AlbumRepository albumRepository;

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

    public List<Song> getSongsFromArtistAlbum(long artistId, long albumId) {
        Album album =  getAlbum(artistId, albumId);

        return album.getSongs();
    }

    public Song saveSongsFromArtistAlbum(long artistId, long albumId, Song song) {
        Album album =  getAlbum(artistId, albumId);

        album.getSongs().add(song);
        albumRepository.save(album);

        return song;
    }

    public Album getAlbum(long artistId, long albumId) {
        return artistRepository.findById(artistId).get().getAlbums().stream()
                .filter(value -> albumId == value.getAlbumId())
                .findAny()
                .orElse(null);
    }

    public Album getArtistAlbum(long artistId, long albumId) {
        return  artistRepository.findById(artistId).get().getAlbums().stream()
                .filter(value -> albumId == value.getAlbumId())
                .findAny()
                .orElse(null);
    }
}