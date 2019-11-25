package com.autentia.restservice.repository;

import com.autentia.restservice.model.Album;
import com.autentia.restservice.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface SongRepository extends PagingAndSortingRepository<Song, Long>, JpaSpecificationExecutor<Song> {}
