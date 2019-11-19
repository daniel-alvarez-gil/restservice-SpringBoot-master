package com.autentia.restservice.repository;

import com.autentia.restservice.model.Artist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

public interface ArtistRepository extends JpaRepository<Artist, Long>, JpaSpecificationExecutor<Artist> {}
