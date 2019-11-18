package com.autentia.restservice.dto;

public class SongRate {
    private long id;
    private double rate;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double price) {
        this.rate = price;
    }
}
