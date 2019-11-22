package com.autentia.restservice.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.hateoas.ResourceSupport;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@ApiModel("Model Album")
public class Album extends ResourceSupport {

    @Id
    @GeneratedValue
    @ApiModelProperty(notes = "id of the Album")
    private long albumId;

    @ApiModelProperty(notes = "href Image of the Album")
    private String hrefImage;

    @ApiModelProperty(notes = "title of the Album")
    private String  title;

    @OneToMany(cascade = {CascadeType.ALL})
    @ApiModelProperty(notes = "songs of the User")
    private List<Song> songs;

    public long getAlbumId() {
        return albumId;
    }

    public void setAlbumId(long albumId) {
        this.albumId = albumId;
    }

    public String getHrefImage() {
        return hrefImage;
    }

    public void setHrefImage(String hrefImage) {
        this.hrefImage = hrefImage;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Song> getSongs() {
        return (songs != null) ? songs: new ArrayList<>();
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }

}
