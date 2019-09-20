package com.example.linguachat;

public class Categories {
    private String card_bg;
    private String card_color;
    private String card_desc;
    private String card_level;
    private String card_title;
    private int id;
    private boolean is_completed;
    private boolean is_inprogress;
    private boolean is_started;
    private int progress;

    public Categories() {
        //empty constructor needed
    }

    public Categories(String card_bg, String card_color, String card_desc, String card_level, String card_title, int id,
                      boolean is_completed, boolean is_inprogress, boolean is_started, int progress) {
        this.card_bg = card_bg;
        this.card_color = card_color;
        this.card_desc = card_desc;
        this.card_level = card_level;
        this.card_title = card_title;
        this.id = id;
        this.is_completed = is_completed;
        this.is_inprogress = is_inprogress;
        this.is_started = is_started;
        this.progress = progress;
    }

    String getCard_bg() {
        return card_bg;
    }

    String getCard_color() {
        return card_color;
    }

    String getCard_desc() {
        return card_desc;
    }

    String getCard_level() {
        return card_level;
    }

    String getCard_title() {
        return card_title;
    }

    public int getId() {
        return id;
    }

    boolean isIs_completed() {
        return is_completed;
    }

    boolean isIs_inprogress() {
        return is_inprogress;
    }

    public boolean isIs_started() {
        return is_started;
    }

    public int getProgress() { return progress; }
}


