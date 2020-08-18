package com.sunno.Model;

import java.util.ArrayList;

public class MetaDataModel {
    private ArrayList<Genre> genre;
    private ArrayList<ArtistModel> artists;
    private ArrayList<AlbumModel> albums;
    private ArrayList<PlaylistModel> playlists;


    public MetaDataModel(ArrayList<Genre> genre, ArrayList<ArtistModel> artists, ArrayList<AlbumModel> albums, ArrayList<PlaylistModel> playlists) {
        this.genre = genre;
        this.artists = artists;
        this.albums = albums;
        this.playlists = playlists;
    }

    public MetaDataModel() {
    }

    public ArrayList<Genre> getGenre() {
        return genre;
    }

    public void setGenre(ArrayList<Genre> genre) {
        this.genre = genre;
    }

    public ArrayList<ArtistModel> getArtists() {
        return artists;
    }

    public void setArtists(ArrayList<ArtistModel> artists) {
        this.artists = artists;
    }

    public ArrayList<AlbumModel> getAlbums() {
        return albums;
    }

    public void setAlbums(ArrayList<AlbumModel> albums) {
        this.albums = albums;
    }

    public ArrayList<PlaylistModel> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(ArrayList<PlaylistModel> playlists) {
        this.playlists = playlists;
    }
}
