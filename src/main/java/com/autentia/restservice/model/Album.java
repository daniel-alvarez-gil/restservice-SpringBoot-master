package com.autentia.restservice.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.List;

@Entity
@ApiModel("Model Album")
public class Album {

    @Id
    @GeneratedValue
    @ApiModelProperty(notes = "id of the Album")
    private long id;

    @ApiModelProperty(notes = "href Image of the Album")
    private String hrefImage;

    @ApiModelProperty(notes = "title of the Album")
    private String  title;

    @OneToMany(cascade = {CascadeType.ALL})
    @ApiModelProperty(notes = "songs of the User")
    private List<Song> songs;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
        return songs;
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }

}
