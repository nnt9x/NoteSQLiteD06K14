package com.example.notesqlited06k14.model;

public class Note {
    public long id;
    public String title;

    public Note(String title) {
        this.title = title;
    }

    public Note(long id, String title) {
        this.id = id;
        this.title = title;
    }
}
