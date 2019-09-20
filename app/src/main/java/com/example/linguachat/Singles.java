package com.example.linguachat;

public class Singles {
    private int id;
    private String singles_count;
    private String singles_icon;
    private String singles_text;

    public Singles() {
        //empty constructor needed
    }

    public Singles(int id, String singles_count, String singles_icon, String singles_text) {
        this.id = id;
        this.singles_count = singles_count;
        this.singles_icon = singles_icon;
        this.singles_text = singles_text;
    }

    public int getId() {
        return id;
    }

    public String getSingles_count() {
        return singles_count;
    }

    public String getSingles_icon() {
        return singles_icon;
    }

    public String getSingles_text() {
        return singles_text;
    }
}
