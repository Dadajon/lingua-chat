package com.example.linguachat;

public class Sections {
    private String section_icon;
    private int section_id;
    private String section_title;

    public Sections() {
        //empty constructor needed
    }

    public Sections(String section_icon, int section_id, String section_title) {
        this.section_icon = section_icon;
        this.section_id = section_id;
        this.section_title = section_title;
    }

    public String getSection_icon() {
        return section_icon;
    }

    public int getSection_id() {
        return section_id;
    }

    public String getSection_title() {
        return section_title;
    }
}
