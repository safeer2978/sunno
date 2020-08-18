package com.sunno.Model;
public class Genre {
    private int id;
    private String img_url;
    private String name;

    public Genre(int id, String img_url, String name) {
        this.id = id;
        this.img_url = img_url;
        this.name = name;
    }
    public Genre() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

