package com.sunno.Model;

import java.util.ArrayList;

public class ArtistModel {
    private int id;
    private String name;
    private String img_url;
    private ArrayList<AlbumModel> albums;
    private ArrayList<TrackModel> tracks;

    public ArtistModel(int id, String name, String img_url, ArrayList<AlbumModel> albums, ArrayList<TrackModel> tracks) {
        this.id = id;
        this.name = name;
        this.img_url = img_url;
        this.albums = albums;
        this.tracks = tracks;
    }

    public ArtistModel() {
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

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public ArrayList<AlbumModel> getAlbums() {
        return albums;
    }

    public void setAlbums(ArrayList<AlbumModel> albums) {
        this.albums = albums;
    }

    public ArrayList<TrackModel> getTracks() {
        return tracks;
    }

    public void setTracks(ArrayList<TrackModel> tracks) {
        this.tracks = tracks;
    }
}

/*          "id": 1,
            "name": "Ariana Grande",
            "image_url": "https://upload.wikimedia.org/wikipedia/commons/d/dd/Ariana_Grande_Grammys_Red_Carpet_2020.png",
            "albums": [
                {
                    "id": 3,
                    "name": "7 rings",
                    "image_url": "https://upload.wikimedia.org/wikipedia/commons/d/dd/Ariana_Grande_Grammys_Red_Carpet_2020.png",
                    "genre": {
                        "id": 2,
                        "img_url": "https://upload.wikimedia.org/wikipedia/en/1/1d/Altered_Images.jpg",
                        "name": "Pop"
                    },
                    "combination": "7 rings1"
                }
            ],
            "tracks": [
                {
                    "id": "2a6dd2b1-65e0-37a2-b358-2d314c2b5e17",
                    "title": "7 rings",
                    "album": {
                        "id": 3,
                        "name": "7 rings",
                        "image_url": "https://upload.wikimedia.org/wikipedia/commons/d/dd/Ariana_Grande_Grammys_Red_Carpet_2020.png",
                        "genre": {
                            "id": 2,
                            "img_url": "https://upload.wikimedia.org/wikipedia/en/1/1d/Altered_Images.jpg",
                            "name": "Pop"
                        },
                        "combination": "7 rings1"
                    }
                }
            ]
 */
