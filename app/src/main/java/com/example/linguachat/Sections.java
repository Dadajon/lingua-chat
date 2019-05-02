package com.example.linguachat;

public class Sections {
    private boolean section_completed;
    private int section_id;
    private String section_title;

    public Sections() {
        //empty constructor needed
    }

    public Sections(boolean section_completed, int section_id, String section_title) {
        this.section_completed = section_completed;
        this.section_id = section_id;
        this.section_title = section_title;
    }

    public boolean isSection_completed() {
        return section_completed;
    }

    public int getSection_id() {
        return section_id;
    }

    public String getSection_title() {
        return section_title;
    }
}
