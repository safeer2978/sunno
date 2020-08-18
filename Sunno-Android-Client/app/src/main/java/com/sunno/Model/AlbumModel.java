package com.sunno.Model;

import java.util.ArrayList;
import java.util.List;

public class AlbumModel {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public List<Genre> getGenre() {
        return genre;
    }

    public void setGenre(List<Genre> genre) {
        this.genre = genre;
    }

    public String getCombination() {
        return Combination;
    }

    public void setCombination(String combination) {
        Combination = combination;
    }

    private int id;
    private String name;
    private String img_url;
    private List<Genre> genre;
    private String Combination;
}

/*

                    "id": 59,
                    "name": "Sorry For Party Rocking (Deluxe Version)",
                    "image_url": "https://i.imgur.com/nszu54A.jpg",
                    "genre": {
                        "id": 58,
                        "img_url": "https://i.imgur.com/nszu54A.jpg",
                        "name": "Pop\\Dance\\Club"
                    },
                    "combination": "Sorry For Party Rocking (Deluxe Version)57"
 */
