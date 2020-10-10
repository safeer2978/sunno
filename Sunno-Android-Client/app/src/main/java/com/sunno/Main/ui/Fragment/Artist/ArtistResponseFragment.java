package com.sunno.Main.ui.Fragment.Artist;

import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.sunno.Config.CircleTransform;
import com.sunno.Main.Model.Entities.ArtistModel;
import com.sunno.Main.ui.MainActivityViewModel;
import com.sunno.Main.ui.OnCategoryClickListener;
import com.sunno.R;

import java.util.Objects;

public class ArtistResponseFragment extends Fragment {

    ImageView imageView;
    RecyclerView albumRv;
    RecyclerView artistRv;

    OnCategoryClickListener onCategoryClickListener;
    MainActivityViewModel viewModel;
    int artistId;

    public ArtistResponseFragment(OnCategoryClickListener onCategoryClickListener, int artistId) {
        this.onCategoryClickListener = onCategoryClickListener;
        this.artistId = artistId;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_artist_response,container,false);
    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        artistRv = view.findViewById(R.id.fat_artist_recyclerView);
        albumRv = view.findViewById(R.id.fat_album_recyclerView);
        imageView = view.findViewById(R.id.fat_imageView);

        viewModel = ViewModelProviders.of(Objects.requireNonNull(getActivity())).get(MainActivityViewModel.class);
        viewModel.setArtist(artistId);

        AlbumsAdapter albumsAdapter = new AlbumsAdapter(viewModel.getAlbums(),onCategoryClickListener);
        albumRv.setAdapter(albumsAdapter);
        albumRv.setLayoutManager(new LinearLayoutManager(getContext()));

        ArtistAdapter artistAdapter = new ArtistAdapter(viewModel.getArtistFromSameGenre(),onCategoryClickListener);
        artistRv.setAdapter(artistAdapter);
        artistRv.setLayoutManager(new LinearLayoutManager(getContext()));


        String url = viewModel.getArtistImageUrl();

        if(url.length()!=0){
            Picasso.get()
                    .load(url)
                    .transform(new CircleTransform())
                    .resize(300,300)
                    .into(imageView);
        }
        else{
            Picasso.get()
                    .load("https://i.imgur.com/nszu54A.jpg")
                    .resize(300,300)
                    .transform(new CircleTransform())
                    .into(imageView);

        }
    }
}
