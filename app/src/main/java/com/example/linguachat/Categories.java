package com.example.linguachat;

public class Categories {
    private String card_bg;
    private String card_color;
    private String card_desc;
    private String card_level;
    private String card_title;
    private int id;

    public Categories() {
        //empty constructor needed
    }

    public Categories(String card_bg, String card_color, String card_desc, String card_level, String card_title, int id) {
        this.card_bg = card_bg;
        this.card_color = card_color;
        this.card_desc = card_desc;
        this.card_level = card_level;
        this.card_title = card_title;
        this.id = id;
    }

    public String getCard_bg() {
        return card_bg;
    }

    public String getCard_color() {
        return card_color;
    }

    public String getCard_desc() {
        return card_desc;
    }

    public String getCard_level() {
        return card_level;
    }

    public String getCard_title() {
        return card_title;
    }

    public int getId() {
        return id;
    }
}


