package com.autentia.restservice.dto;

public class SongRate {
    private String title;
    private double rate;

    public String getId() {
        return title;
    }

    public void setId(String title) {
        this.title = title;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double price) {
        this.rate = price;
    }
}
