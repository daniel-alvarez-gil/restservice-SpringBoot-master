package com.autentia.restservice.service;

import com.autentia.restservice.model.Song;
import com.autentia.restservice.repository.SongRepository;
import com.autentia.restservice.exception.SongNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class SongService{

    @Autowired
    SongRepository songRepository;

    public List<Song> getAllSongs() {
        List<Song> songs = new ArrayList<>();
        songRepository.findAll().forEach(song -> songs.add(song));
        return songs;
    }

    public List<Song> getAllSongs(Specification<Song> specification) {
        List<Song> songs = new ArrayList<>();
        songRepository.findAll(specification).forEach(song -> songs.add(song));
        return songs;
    }

    public Song getSongById(long id) {
        return songRepository.findById(id).orElseThrow(() -> new SongNotFoundException(id));
    }

    public List<Song> search(String genre, Date release) {
        List<Song> songs = new ArrayList<>();
        songRepository.findAll().forEach(song -> {
            if((genre != null && song.getGenre().contains(genre)) ||
                    (release != null && (song.getRelease().compareTo(release) == 0))) {
                songs.add(song);
            }
        });
        return songs;
    }

    public void saveOrUpdate(Song song) {
        songRepository.save(song);
    }

    public void delete(long id) {
        songRepository.deleteById(id);
    }

    public Page<Song> findAllPage(Pageable pageable) {
        return songRepository.findAll(pageable);
    }
}