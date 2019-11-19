package com.autentia.restservice.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;

import javax.persistence.*;
import java.util.Date;

@Entity
@ApiModel("Model Song")
//@JsonFilter("fieldFilter")
public class Song extends ResourceSupport {

    @Id
    @GeneratedValue
    @ApiModelProperty(notes = "id of the Song")
    private long songId;

    @Column()
    @ApiModelProperty(notes = "href of the Song")
    private String href;

    @Column()
    @ApiModelProperty(notes = "title of the Song")
    private String title;

    @Column()
    @ApiModelProperty(notes = "release date of the Song")
    private Date release;

    @Column
    @ApiModelProperty(notes = "genre of the Song")
    private String genre;

    @Column
    @ApiModelProperty(notes = "price of the Song")
    private double price;

    public long getSongId() {
        return songId;
    }

    public void setSongId(long songId) {
        this.songId = songId;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getRelease() {
        return release;
    }

    public void setRelease(Date release) {
        this.release = release;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
