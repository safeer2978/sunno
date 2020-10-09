package com.sunno.Main.Model.Entities;

public class AlbumModel {

    private int id;
    private String name;
    private String image_url;
    private Genre genre;
    private String combination;




    public AlbumModel(int id, String name, String image_url, Genre genre, String combination) {
        this.id = id;
        this.name = name;
        this.image_url = image_url;
        this.genre = genre;
        this.combination = combination;
    }

    public AlbumModel() {
    }

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

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public String getCombination() {
        return combination;
    }

    public void setCombination(String combination) {
        this.combination = combination;
    }

    @Override
    public String toString() {
        return "AlbumModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", image_url='" + image_url + '\'' +
                ", genre=" + genre +
                ", combination='" + combination + '\'' +
                '}';
    }
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
