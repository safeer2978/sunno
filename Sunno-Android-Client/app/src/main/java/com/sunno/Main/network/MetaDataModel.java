package com.sunno.Main.network;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.sunno.Main.Model.Entities.AlbumModel;
import com.sunno.Main.Model.Entities.ArtistModel;
import com.sunno.Main.Model.Entities.Genre;

import java.util.List;

public class MetaDataModel {
    @SerializedName("genre")
    @Expose
    private List<Genre> genre;

    private List<ArtistModel> artists;

    private List<AlbumModel> albums;




    public List<ArtistModel> getArtists() {
        return artists;
    }
    public void setArtists(List<ArtistModel> artists) {
        this.artists = artists;
    }
    public List<AlbumModel> getAlbums() {
        return albums;
    }
    public void setAlbums(List<AlbumModel> albums) {
        this.albums = albums;
    }
    public List<Genre> getGenre() {
        return genre;
    }
    public void setGenre(List<Genre> genre) {
        this.genre = genre;
    }

}
