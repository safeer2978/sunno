package com.sunno.Main.ui;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.sunno.Main.Model.Entities.AlbumModel;
import com.sunno.Main.Model.Entities.ArtistModel;
import com.sunno.Main.Model.Entities.Genre;
import com.sunno.Main.Model.Repository;

import java.util.ArrayList;
import java.util.List;

public class MainActivityViewModel extends AndroidViewModel {

    ArtistModel artist;
    AlbumModel album;

    Repository repository;
    public MainActivityViewModel(@NonNull Application application) {
        super(application);repository = Repository.getInstance(application);
    }

    public void setArtist(int artistId){
        List<ArtistModel> artistModelList=repository.getArtistsList();
        for(ArtistModel a: artistModelList)
            if(a.getId()==artistId){
                artist = a;
                break;
            }

        if(artist!=null && artist.getAlbums().size()>0){
            album= artist.getAlbums().get(0);
        }
    }

    public String getArtistImageUrl(){
        return artist==null?"":artist.getImage_url();
    }

    public List<ArtistModel> getArtistFromSameGenre(){
        List<ArtistModel> list = new ArrayList<>();
        if(artist!=null){
            List<ArtistModel> artistModelList=repository.getArtistsList();
            for(ArtistModel a: artistModelList){
                List<AlbumModel> albumModels = a.getAlbums();
                for(AlbumModel am: albumModels)
                {
                    if(album!=null && am.getGenre()==album.getGenre())
                        list.add(a);
                }
            }
        }
        return list;
    }

    public List<AlbumModel> getAlbums(){
        return artist!=null?artist.getAlbums():new ArrayList<AlbumModel>();
    }

    public List<AlbumModel>  getAlbums(Genre genre){
        List<AlbumModel> list = new ArrayList<>();
        for(AlbumModel a:repository.getAlbumList()){
            if(a.getGenre().getId() == genre.getId())
                list.add(a);
        }
        return list;
    }
}
