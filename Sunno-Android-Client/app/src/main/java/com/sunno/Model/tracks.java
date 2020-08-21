package com.sunno.Model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;


public class tracks {
    private int id;
    private String title;
    private ArrayList<AlbumModel> album;
    private String combination;

    public tracks(int id, String title, ArrayList<AlbumModel> album, String combination) {
        this.id = id;
        this.title = title;
        this.album = album;
        this.combination = combination;
    }

    public tracks() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<AlbumModel> getAlbum() {
        return album;
    }

    public void setAlbum(ArrayList<AlbumModel> album) {
        this.album = album;
    }

    public String getCombination() {
        return combination;
    }

    public void setCombination(String combination) {
        this.combination = combination;
    }
}
/*
                    "id": "86342506-7c44-35e9-9c23-8950b79b0c75",
                    "title": "Sexy And I Know It",
                    "album": {
                        "id": 59,
                        "name": "Sorry For Party Rocking (Deluxe Version)",
                        "image_url": "https://i.imgur.com/nszu54A.jpg",
                        "genre": {
                            "id": 58,
                            "img_url": "https://i.imgur.com/nszu54A.jpg",
                            "name": "Pop\\Dance\\Club"
                        },
                        "combination": "Sorry For Party Rocking (Deluxe Version)57"
                    }
 */
