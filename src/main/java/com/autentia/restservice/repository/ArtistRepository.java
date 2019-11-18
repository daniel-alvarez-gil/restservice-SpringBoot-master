package com.autentia.restservice.repository;

import com.autentia.restservice.model.Artist;
import org.springframework.data.repository.CrudRepository;

public interface ArtistRepository extends CrudRepository<Artist, Integer> {}
