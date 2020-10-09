package com.sunno.Main.ui.Fragment.Home;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.sunno.Main.Model.Entities.AlbumModel;
import com.sunno.Main.Model.Entities.ArtistModel;
import com.sunno.Main.Model.Entities.Genre;
import com.sunno.Main.Model.Repository;

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
