package com.autentia.restservice.repository;

import com.autentia.restservice.model.Album;
import org.springframework.data.repository.CrudRepository;

public interface AlbumRepository extends CrudRepository<Album, Integer> {}
