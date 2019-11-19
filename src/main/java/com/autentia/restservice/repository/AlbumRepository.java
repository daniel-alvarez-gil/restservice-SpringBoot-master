package com.autentia.restservice.repository;

import com.autentia.restservice.model.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

public interface AlbumRepository extends JpaRepository<Album, Long>, JpaSpecificationExecutor<Album> {}
