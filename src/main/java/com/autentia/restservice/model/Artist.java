package com.autentia.restservice.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.List;

@Entity
@ApiModel("Model Artist")
public class Artist {

    @Id
    @GeneratedValue
    @ApiModelProperty(notes = "id of the Artist")
    private long id;
    @Column
    @ApiModelProperty(notes = "href Image of the Artist")
    private String hrefImage;
    @Column
    @ApiModelProperty(notes = "name of the Artist")
    private String  artistName;
    @OneToMany(cascade = {CascadeType.ALL})
    @ApiModelProperty(notes = "albums of the Artist")
    private List<Album> albums;

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

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public List<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(List<Album> albums) {
        this.albums = albums;
    }
}
