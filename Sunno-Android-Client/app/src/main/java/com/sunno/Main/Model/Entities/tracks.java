package com.sunno.Main.Model.Entities;

import com.google.gson.annotations.SerializedName;
import com.sunno.Main.Model.Entities.AlbumModel;

import java.util.ArrayList;
import java.util.List;

public class tracks {
    private String id;
    private String title;
    private AlbumModel album;

    @SerializedName("combination")
    private String combination;


    public tracks(String id, String title, AlbumModel album, String combination) {
        this.id = id;
        this.title = title;
        this.album = album;
        this.combination = combination;
    }

    public tracks() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public AlbumModel getAlbum() {
        return album;
    }

    public void setAlbum(AlbumModel album) {
        this.album = album;
    }

    public String getCombination() {
        return combination;
    }

    public void setCombination(String combination) {
        this.combination = combination;
    }

    @Override
    public String toString() {
        return "tracks{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", album=" + album +
                ", combination='" + combination + '\'' +
                '}';
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
