package com.sunno;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.sunno.Model.AlbumModel;
import com.sunno.Model.ArtistModel;
import com.sunno.Model.Genre;

import java.util.List;

public class HomeFragmentViewModel extends AndroidViewModel {

    Repository repository;

    public HomeFragmentViewModel(@NonNull Application application) {
        super(application);
        repository = Repository.getInstance(application);
    }


    public List<Genre> getGenreList(){
        return repository.getGenreList();
    }

    public List<AlbumModel> getAlbumList(){
        return repository.getAlbumList();
    }

    public List<ArtistModel> getArtistList(){ return repository.getArtistsList(); }

}
