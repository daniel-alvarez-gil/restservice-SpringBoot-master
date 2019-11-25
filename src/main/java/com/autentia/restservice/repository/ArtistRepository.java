package com.autentia.restservice.repository;

import com.autentia.restservice.model.Album;
import com.autentia.restservice.model.Artist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ArtistRepository extends
        PagingAndSortingRepository<Artist, Long>, JpaSpecificationExecutor<Artist> {}
