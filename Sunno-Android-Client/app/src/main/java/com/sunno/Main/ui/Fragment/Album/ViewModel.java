package com.sunno.Main.ui.Fragment.Album;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.sunno.Main.AddTrackInterface;
import com.sunno.Main.Model.Entities.AlbumModel;
import com.sunno.Main.Model.Entities.ArtistModel;
import com.sunno.Main.Model.Entities.tracks;
import com.sunno.Main.Model.Repository;

import java.util.ArrayList;
import java.util.List;

public class ViewModel extends AndroidViewModel {
    Repository repository;
    AlbumModel album;
    ArtistModel artist;
    public ViewModel(@NonNull Application application) {
        super(application);
        repository = Repository.getInstance(application);
    }

    void setAlbum(int albumId){
        Log.d("ADAPTER", "albumID:"+albumId);
        List<ArtistModel> artistModels = repository.getArtistsList();
            boolean flag = false;
            for(ArtistModel a: artistModels){
                List<AlbumModel> albumModels = a.getAlbums();
                for(AlbumModel b : albumModels){
                    if(b.getId()==albumId){
                        album=b;
                        artist=a;
                        Log.d("ADAPTER", "artist set:"+artist.toString());
                        flag=true;
                        break;
                    }
                }
                if(flag)
                    break;
            }
    }


    String getUrl(){
        return album==null?"":album.getImage_url();
    }

    List<tracks> getTracks(){
        List<tracks> tracks=new ArrayList<>();

        if(artist!=null){
        for(tracks t: artist.getTracks())
            if(t.getAlbum().getId() == album.getId())
                tracks.add(t);
        }
        return tracks;

    }


    AddTrackInterface addTrackInterface;

    public void setAddTrackInterface(AddTrackInterface addTrackInterface) {
        this.addTrackInterface = addTrackInterface;
    }

    AddTrackInterface getAddTrackInterface(){
        return addTrackInterface;
    }


}
