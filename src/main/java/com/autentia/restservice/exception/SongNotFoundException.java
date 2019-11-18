package com.autentia.restservice.exception;

public class SongNotFoundException extends RuntimeException  {
    public SongNotFoundException(long id) {
        super("Song id not found : " + id);
    }
}
