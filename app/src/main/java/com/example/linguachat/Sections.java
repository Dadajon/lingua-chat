package com.example.linguachat;

public class Sections {
    private boolean section_completed;
    private int section_id;
    private boolean section_started;
    private String section_title;

    public Sections() {
        //empty constructor needed
    }

    public Sections(boolean section_completed, int section_id, boolean section_started, String section_title) {
        this.section_completed = section_completed;
        this.section_id = section_id;
        this.section_started = section_started;
        this.section_title = section_title;
    }

    public boolean isSection_completed() {
        return section_completed;
    }

    public int getSection_id() {
        return section_id;
    }

    public boolean isSection_started() {
        return section_started;
    }

    public String getSection_title() {
        return section_title;
    }
}
