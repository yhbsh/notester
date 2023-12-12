package com.example.notester;

public class Note {
    private final int id;
    private final String title;

    public Note(int id, String title) {
        this.id = id;
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }
}
