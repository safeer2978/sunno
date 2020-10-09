package com.sunno.Main.Model.Entities;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class ArtistModel {
    private int id;
    private String name;
    private String image_url;

    @SerializedName("albums")
    private List<AlbumModel> albums;
    @SerializedName("tracks")
    private List<tracks> tracks;

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public List<AlbumModel> getAlbums() {
        return albums;
    }

    public void setAlbums(List<AlbumModel> albums) {
        this.albums = albums;
    }

    public List<com.sunno.Main.Model.Entities.tracks> getTracks() {
        return tracks;
    }

    public void setTracks(List<com.sunno.Main.Model.Entities.tracks> tracks) {
        this.tracks = tracks;
    }

    public ArtistModel(int id, String name, String img_url ) {
        this.id = id;
        this.name = name;
        this.image_url = img_url;
     //   this.albums = albums;
      //  this.tracks = tracks;
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
        return image_url;
    }

    public void setImg_url(String img_url) {
        this.image_url = img_url;
    }

    @Override
    public String toString() {
        return "ArtistModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", image_url='" + image_url + '\'' +
                ", albums=" + albums.get(0).toString() +
                ", tracks=" + tracks.get(0).toString() +
                '}';
    }


//    public ArrayList<AlbumModel> getAlbums() {
//        return albums;
//    }

//    public void setAlbums(ArrayList<AlbumModel> albums) {
//        this.albums = albums;
//    }

//    public List<com.sunno.Main.Model.Entities.tracks> getTracks() {
//        return tracks;
//    }
//
//    public void setTracks(List<com.sunno.Main.Model.Entities.tracks> tracks) {
//        this.tracks = tracks;
//    }

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
