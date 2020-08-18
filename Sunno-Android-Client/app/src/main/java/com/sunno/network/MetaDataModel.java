package com.sunno.network;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.sunno.Model.AlbumModel;
import com.sunno.Model.ArtistModel;
import com.sunno.Model.Genre;
import com.sunno.Model.PlaylistModel;

import java.util.ArrayList;
import java.util.List;

public class MetaDataModel {

    public List<Genre> getGenre() {
        return genre;
    }

    public void setGenre(List<Genre> genre) {
        this.genre = genre;
    }

    @SerializedName("genre")
    @Expose
    private List<Genre> genre;

}
