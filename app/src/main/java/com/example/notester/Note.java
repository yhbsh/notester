package com.example.notester;

public class Note {
    private final Integer id;
    private final String title;

    public Note() {
        this.id = 0;
        this.title = "";
    }

    public Note(Integer id, String title) {
        this.id = id;
        this.title = title;
    }


    public String getTitle() {
        return title;
    }

}
